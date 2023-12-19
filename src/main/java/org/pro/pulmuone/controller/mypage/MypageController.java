package org.pro.pulmuone.controller.mypage;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageListDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
import org.pro.pulmuone.domain.order.OrderAddrBookDTO;
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

	@RequestMapping("/mypage/order/box")
	public String orderBox(Model model
								, @RequestParam(name = "startSearchDate", required = false) String startSearchDate
								, @RequestParam(name = "endSearchDate", required = false) String endSearchDate) {
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
		
		return "mypage/order/box.tiles";
	}
	
	@RequestMapping("mypage/action/review")
	public String review(ProductsDTO dto, Model model, Principal principal) throws ClassNotFoundException, SQLException {
		log.info("reviewlist" );		
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> reviewlist = this.mapper.reviewlist(dto);
		model.addAttribute("reviewlist",reviewlist);
		return "mypage/review/list.tiles";
	}
	
	@RequestMapping("/mypage/action/review/myWriteReview")
	public String myWriteReview(ProductsDTO dto, Model model, Principal principal) throws ClassNotFoundException, SQLException {
		log.info("myreviewlist" );		
		dto.setMember_id( principal.getName() );
		List<ProductsDTO> myreviewlist = this.mapper.myreviewlist(dto);
		model.addAttribute("myreviewlist",myreviewlist);
		return "mypage/review/writelist.tiles";
	}
	
}
