package org.pro.pulmuone.controller.mypage;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pro.pulmuone.domain.product.ProductsDTO;
import org.pro.pulmuone.mapper.product.ProductMapper;
import org.pro.pulmuone.service.inquiry.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("mypage")
	public String summary(HttpServletRequest request, ProductsDTO dto, Model model, Principal principal) throws SQLException {
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
