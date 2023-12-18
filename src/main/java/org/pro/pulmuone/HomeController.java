package org.pro.pulmuone;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.product.ProductsDTO;
import org.pro.pulmuone.mapper.cart.CartMapper;
import org.pro.pulmuone.mapper.main.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private MainMapper mainMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	public HomeController() {
		super();
	}
	@RequestMapping("index")
	public String home(Model model) throws Exception {	  
	  log.warn("> INDEX START ...");
		List<ProductsDTO> mainbestlist = this.mainMapper.get();				
		model.addAttribute("mainbestlist", mainbestlist );
		return "home.index";
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("cart")
	public String cart(HttpServletRequest request, Model model, CartVO vo, Principal principal) throws Exception {		
	  String uri = request.getRequestURI();
		log.info("4444444444444444444" + uri);		  
		vo.setMember_id(principal.getName());		
		int totalCount = this.cartMapper.cart(vo);
//		model.addAttribute("list", list );		
		log.warn(totalCount+" : asdkdsjkadsadsklads");
		request.setAttribute("totalCount", totalCount);
		return "layouts/header.tiles";
		
	}
}
