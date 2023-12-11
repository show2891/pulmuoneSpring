package org.pro.pulmuone.controller.cart;

import java.sql.SQLException;
import java.util.List;

import org.pro.pulmuone.domain.product.ProductsDTO;
import org.pro.pulmuone.mapper.cart.CartMapper;
import org.pro.pulmuone.mapper.main.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cart")
public class CartController {
	
/*
/cart/daily.do = servlets.cart.command.CartDaily
/cart/box.do = servlets.cart.command.CartBox
/cart/kidcart.do = servlets.cart.command.Kidscart
 */
	
	@Autowired
	private CartMapper cartMapper;
	
	// 매일 장바구니, 키즈
	
	@RequestMapping("/daily")
	public String daily() throws ClassNotFoundException, SQLException {
		
		
		log.info("> DailyCart Start");
		return "cart/daily.tiles";
	}
	// 택배 장바구니	
	@RequestMapping("/box")
	public String box() throws ClassNotFoundException, SQLException {
		
		log.info("> BoxCart Start");
		return "cart/box.tiles";
	}

}
