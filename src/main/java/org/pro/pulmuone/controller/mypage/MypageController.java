package org.pro.pulmuone.controller.mypage;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageListDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.HaveCouponDTO;
import org.pro.pulmuone.domain.order.OrderAddrBookDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderProductsDTO;
import org.pro.pulmuone.domain.order.box.BoxPayDTO;
import org.pro.pulmuone.domain.order.box.BoxShipDTO;
import org.pro.pulmuone.domain.order.daily.DrkHistoryDTO;
import org.pro.pulmuone.domain.order.daily.DrkShipDTO;
import org.pro.pulmuone.domain.product.ProductsDTO;
import org.pro.pulmuone.mapper.product.ProductMapper;
import org.pro.pulmuone.service.inquiry.InquiryService;
import org.pro.pulmuone.service.mypage.order.BoxOrderMypageServiceImpl;
import org.pro.pulmuone.service.mypage.order.DailyOrderMypageServiceImpl;
import org.pro.pulmuone.service.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/*")
@Log4j
public class MypageController {

	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private DailyOrderMypageServiceImpl dailyOrderMypageServiceImpl;
	
	@Autowired
	private BoxOrderMypageServiceImpl boxOrderMypageServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@GetMapping("mypage")
	public String summary(HttpServletRequest request, Model model, ProductsDTO dto, Principal principal) throws SQLException {
		log.warn("> MypageController mypage()...");
		
//		1. 리뷰 카운트를 위한 쿼리 최선의 방법은 무엇일까 
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> reviewlist = this.mapper.reviewlist(dto);
		model.addAttribute("reviewlist",reviewlist);
//		2. 문의 카운트를 위한 쿼리 최선의 방법은 무엇일까
		String userId = principal.getName();
		int totalCount = 0;
		 totalCount = inquiryService.selectCount(userId, "all");
		 request.setAttribute("totalCount", totalCount);
		
		// >> member_no 가져오기 <<
		// 현재 사용자의 인증 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        // 사용자 id 가져오기
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } // if
		        
        // member_no 가져오기
        OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		int member_no = member.getMember_no();
		
		
		// >> 매일배송 <<
		DrkOrderMypageDTO drkOrderMypageDTO = dailyOrderMypageServiceImpl.selectDailyOrder(member_no);
		model.addAttribute("drkOrderMypageDTO", drkOrderMypageDTO);
		
		
		// >> 택배배송 <<
		List<Integer> boxOrderStatus = boxOrderMypageServiceImpl.getBoxOrderStatus(member_no);
		model.addAttribute("boxOrderStatus", boxOrderStatus);
		
		List<BoxOrderMypageDTO> boxOrderMypageList = boxOrderMypageServiceImpl.selectBoxOrder(member_no);
		model.addAttribute("boxOrderMypageList", boxOrderMypageList);
		
		
		return "mypage/home/userSummmary.tiles";
	}
	
	@RequestMapping("mypage/product/list")
	public String wishlist(ProductsDTO dto, Model model, Principal principal) throws ClassNotFoundException, SQLException {
		log.info("wishlist" );		
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> wishlist = this.mapper.wishlist(dto);
		model.addAttribute("wishlist",wishlist);
		return "mypage/wish/list.tiles";
	}
	
	@RequestMapping("/mypage/product/delete")
	public String wishdelete(ProductsDTO dto, Model model, Principal principal
		,@RequestParam(value = "tag", required = false ) String tag) throws ClassNotFoundException, SQLException {
		log.info("wishdelete :::"+ tag);		
		dto.setMember_id( principal.getName() );
		dto.setProducts_tag(tag);
		dto.setClassname(true);

		this.mapper.wishdelete(dto);
		this.mapper.wishupdate(dto);
				
		return "mypage/wish/list.tiles";
		
	}
	
	@RequestMapping("/mypage/drink/drink")
	public String orderDaily(Model model, @RequestParam(name = "drinkingType", required = false) String drinkingType) {
		log.info("> MypageController orderDaily()...");
		
		// >> member_no 가져오기 <<
		// 현재 사용자의 인증 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        // 사용자 id 가져오기
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } // if
				        
        // member_no 가져오기
        OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		int member_no = member.getMember_no();
		
		// >> 음용 정보 가져오기 <<
		List<DrkOrderMypageDTO> drkOrderMypageList = this.dailyOrderMypageServiceImpl.selectDrinkInfos(member_no, drinkingType);
		model.addAttribute("drkOrderMypageList", drkOrderMypageList);
		
		return "mypage/drink/drink.tiles";
	}
	
	@RequestMapping("/mypage/drink/drinks/{drk_order_no}")
	public String orderDailyView(Model model, @PathVariable int drk_order_no) {
		log.info("> MypageController orderDailyView()...");
		
		// >> member_no 가져오기 <<
		// 현재 사용자의 인증 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = "";
		// 사용자 id 가져오기
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			username = userDetails.getUsername();
		} // if

		// member_no 가져오기
		OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		int member_no = member.getMember_no();

		// >> 음용 리스트 가져오기 <<
		List<DrkOrderMypageDTO> drkOrderMypageList = this.dailyOrderMypageServiceImpl.selectDrinkInfos(member_no);
		model.addAttribute("drkOrderMypageList", drkOrderMypageList);
		
		// >> 음용 정보 가져오기 <<
		DrkOrderMypageDTO drkOrderMypageDTO = this.dailyOrderMypageServiceImpl.selectDrinkInfo(drk_order_no);
		model.addAttribute("drkOrderMypageDTO", drkOrderMypageDTO);
		
		// >> 배송지 정보 가져오기 <<
		DrkShipDTO drkShipDTO = this.dailyOrderMypageServiceImpl.selectDrinkShip(drk_order_no);
		model.addAttribute("drkShipDTO", drkShipDTO);
		
		// >> 다음주 음용 상품 가져오기 <<
		List<List<BoxOrderMypageProductsDTO>> boxOrderMypageProductsList = this.dailyOrderMypageServiceImpl.selectNextWeekDrink(drk_order_no);
		model.addAttribute("productsList", boxOrderMypageProductsList);
		
		return "mypage/drink/drinks.tiles";
	}

	@RequestMapping("/mypage/order/box")
	public String orderBox(Model model
								, @RequestParam(name = "startSearchDate", required = false) String startSearchDate
								, @RequestParam(name = "endSearchDate", required = false) String endSearchDate
								, @RequestParam(name = "searchMonth", required = false) String searchMonth) {
		log.info("> MypageController orderBox()...");
		
		// >> member_no 가져오기 <<
		// 현재 사용자의 인증 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        // 사용자 id 가져오기
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } // if
				        
        // member_no 가져오기
        OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		int member_no = member.getMember_no();
		
		// >> 음용 정보 가져오기 <<
		List<BoxOrderMypageListDTO> boxOrderMypageList = this.boxOrderMypageServiceImpl.selectBoxInfos(member_no, startSearchDate, endSearchDate);
		model.addAttribute("boxOrderMypageList", boxOrderMypageList);
		
		// >> startSearchDate, endSearchDate 출력 <<
		model.addAttribute("searchMonth", searchMonth);
		model.addAttribute("startSearchDate", startSearchDate);
		model.addAttribute("endSearchDate", endSearchDate);
		
		return "mypage/order/box.tiles";
	}
	
	@RequestMapping("/mypage/order/box/{box_order_no}")
	public String orderBoxView(Model model, @PathVariable int box_order_no) {
		log.info("> MypageController orderBoxView()...");
		
		// >> 음용 정보 가져오기 <<
		BoxOrderMypageListDTO boxOrderMypageDTO = this.boxOrderMypageServiceImpl.selectBoxInfo(box_order_no);
		model.addAttribute("boxOrderMypageDTO", boxOrderMypageDTO);
		
		// >> 배송지 정보 가져오기 <<
		BoxShipDTO boxShipDTO = this.boxOrderMypageServiceImpl.selectBoxShip(box_order_no);
		model.addAttribute("boxShipDTO", boxShipDTO);
		
		// >> 결제 정보 가져오기 <<
		BoxPayDTO boxPayDTO = this.boxOrderMypageServiceImpl.selectBoxPay(box_order_no);
		model.addAttribute("boxPayDTO", boxPayDTO);
		
		// >> 사용한 쿠폰 리스트 가져오기 <<
		List<CouponDTO> usedCouponList = this.boxOrderMypageServiceImpl.selectUsedCouponList(boxPayDTO.getBox_pay_no());
		model.addAttribute("usedCouponList", usedCouponList);
		
		return "mypage/order/boxView.tiles";
	}
	
	@RequestMapping("/mypage/order/box/cancel/{box_order_no}")
	public String orderBoxCancel(Model model, @PathVariable int box_order_no) {
		log.info("> MypageController orderBoxCancel()...");
		
		// >> 음용 정보 가져오기 <<
		BoxOrderMypageListDTO boxOrderMypageDTO = this.boxOrderMypageServiceImpl.selectBoxInfo(box_order_no);
		model.addAttribute("boxOrderMypageDTO", boxOrderMypageDTO);
		
		// >> 결제 정보 가져오기 <<
		BoxPayDTO boxPayDTO = this.boxOrderMypageServiceImpl.selectBoxPay(box_order_no);
		model.addAttribute("boxPayDTO", boxPayDTO);
		
		return "mypage/order/boxCancel.tiles";
	}
	
	@RequestMapping("/mypage/order/box/canceled/{box_order_no}")
	public String orderBoxCanceled(Model model, @PathVariable int box_order_no) {
		log.info("> MypageController orderBoxCanceled()...");
		
		// >> 음용 정보 가져오기 <<
		BoxOrderMypageListDTO boxOrderMypageDTO = this.boxOrderMypageServiceImpl.selectBoxInfo(box_order_no);
		model.addAttribute("boxOrderMypageDTO", boxOrderMypageDTO);
		
		// >> 결제 정보 가져오기 <<
		BoxPayDTO boxPayDTO = this.boxOrderMypageServiceImpl.selectBoxPay(box_order_no);
		model.addAttribute("boxPayDTO", boxPayDTO);
		
		// >> 주문 취소 처리 <<
		this.boxOrderMypageServiceImpl.updateBoxOrder(box_order_no);
		// this.boxOrderMypageServiceImpl.deleteBoxShip(box_order_no);
		
		// >> 결제 취소 처리 <<
		int box_pay_no = boxPayDTO.getBox_pay_no();
		this.boxOrderMypageServiceImpl.updateBoxPay(box_pay_no);
		
		// >> 쿠폰 사용 취소 처리 <<
		this.boxOrderMypageServiceImpl.updateHaveCoupon(box_pay_no);
		
		return "mypage/order/boxCanceled.tiles";
	}
	
	@RequestMapping("mypage/action/review")
	public String review(ProductsDTO dto, Model model, Principal principal) throws ClassNotFoundException, SQLException {
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> reviewlist = this.mapper.reviewlist(dto);
		model.addAttribute("reviewlist",reviewlist);
		return "mypage/review/list.tiles";
	}
	
	@RequestMapping("/mypage/action/review/myWriteReview")
	public String myWriteReview(ProductsDTO dto, Model model, Principal principal) throws ClassNotFoundException, SQLException {	
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> myreviewlist = this.mapper.myreviewlist(dto);
		model.addAttribute("myreviewlist",myreviewlist);
		return "mypage/review/writelist.tiles";
	}
	
	@GetMapping("mypage/action/review/modify")
	public String reviewmodify(ProductsDTO dto, Model model, Principal principal) throws ClassNotFoundException, SQLException {		
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> myreviewlist = this.mapper.myreviewlist(dto);
		model.addAttribute("myreviewlist",myreviewlist);
		return "mypage/review/list.tiles";
	}
	
	@RequestMapping("mypage/action/review/delete/{idx}")
	public String reviewdelete(ProductsDTO dto, Model model, Principal principal,@PathVariable String idx) throws ClassNotFoundException, SQLException {		
		dto.setMember_id( principal.getName() );
		dto.setReview_no(idx);
		int count = this.mapper.reviewdelete(dto);
		model.addAttribute("count",count);
		return "mypage/review/writelist.tiles";
	}
	
}
