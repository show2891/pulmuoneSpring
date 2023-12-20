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
	
	@RequestMapping("cart")
	public String cart(Model model, CartVO vo, Principal principal) throws Exception {				  
		vo.setMember_id(principal.getName());		
		int count = this.cartMapper.cart(vo);
		model.addAttribute("count", count );				
		return "home.index";
		
	}
}
