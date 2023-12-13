package org.pro.pulmuone.controller.cart;

import java.lang.reflect.Member;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.mapper.cart.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cart/*")
public class CartController {
	
/*
/cart/daily.do = servlets.cart.command.CartDaily
/cart/box.do = servlets.cart.command.CartBox
 */
	
	@Autowired
	private CartMapper cartMapper;
	
	// 매일 장바구니, 키즈(로그인 후 확인 가능)
	@RequestMapping("daily")
	public String daily(CartVO vo, Model model, Principal principal ) throws ClassNotFoundException, SQLException {
		
		log.info("> DailyCart Start");

		vo.setMember_id( principal.getName());
		List<CartVO> list = this.cartMapper.daily(vo);
		
		model.addAttribute("list",list);
		return "cart/daily.tiles";
	}
	
	// 장바구니 삭제
	  @PostMapping("daily")
	  @ResponseBody
	  public String delete( 
			  Principal principal
			 , @RequestParam("products_no") String itemCode
			  , @RequestParam("cart_no") int cartIdx
			  , RedirectAttributes rttr) throws Exception{
		  
		  log.info("delete start : " + itemCode + "/" + cartIdx);
		  
			int deleteCount = 0;
			try {
				
				deleteCount = cartMapper.dailydelete(cartIdx);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if( deleteCount != 1 ) {
				rttr.addFlashAttribute("result", "failed");
				return "redirect:/cart/daily?products_no=" + itemCode +"&cart_no=" + cartIdx;
			}
			
			rttr.addFlashAttribute("result", "success");
			return "redirect:/cart/daily";
			
		}
	  // 장바구니 추가
	  @PostMapping("/daily/add")
	  @ResponseBody
	  public String addcart(CartVO vo, HttpServletRequest request) throws Exception {
		  HttpSession session = request.getSession();
		  MemberDTO dto = (MemberDTO) session.getAttribute("member");
		  if(dto == null) {
			  return "X";
		  }
		  
		  int result = cartMapper.dailyadd(vo);
		  return result + "";
	  }
	
	// 택배 장바구니	
	@RequestMapping("box")
	public String box(CartVO vo, Model model,Principal principal ) throws ClassNotFoundException, SQLException {
		
		log.info("> BoxCart Start");
		vo.setMember_id( principal.getName());
		List<CartVO> list = this.cartMapper.box(vo);
		model.addAttribute("list",list);
		return "cart/box.tiles";
	}
	
	@RequestMapping("save")
	public String save(CartVO vo, Model model ) throws ClassNotFoundException, SQLException {
		
		log.info("> save Start");
		return "cart/box.tiles";
	}

}
