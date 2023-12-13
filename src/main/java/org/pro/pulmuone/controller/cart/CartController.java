package org.pro.pulmuone.controller.cart;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.ParseException;
import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.mapper.cart.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	
	// 매일 장바구니, 키즈

	@RequestMapping("daily")
	public String daily(CartVO vo, Model model) throws ClassNotFoundException, SQLException {
		
		log.info("> DailyCart Start");
		
		
		List<CartVO> list = this.cartMapper.daily(vo);
	
		model.addAttribute("list",list);
		return "cart/daily.tiles";
	}
	
	// 택배 장바구니	
	@RequestMapping("box")
	public String box(CartVO vo, Model model ) throws ClassNotFoundException, SQLException {
		
		log.info("> BoxCart Start");
		List<CartVO> list = this.cartMapper.box(vo);
		model.addAttribute("list",list);
		return "cart/box.tiles";
	}
	
	@RequestMapping("daily/save/{products_no}")
	public String dailycartsave(@PathVariable String products_no, CartVO vo, Model model, Principal principal
		) throws ClassNotFoundException, SQLException {
		log.info("dailycartsave : " + products_no);
		vo.setMon_cnt(1);
		vo.setTue_cnt(1);
		vo.setWed_cnt(1);
		vo.setThu_cnt(1);
		vo.setFir_cnt(1);
		vo.setMember_id(principal.getName());
		vo.setProducts_no(products_no);
		int count = 0 ;
		
		count = this.cartMapper.dailycart(vo);				
		if(count==1) {
		  log.info("성공" );
		  return "product/DailyList.tiles";
		}else {
		  log.info("실패" );
		  return "product/DailyList.tiles";
		}

	}

	@RequestMapping("box/save/{products_no}")
	public String boxcartsave(@PathVariable String products_no, CartVO vo, Model model, Principal principal
		) throws ClassNotFoundException, SQLException {
		log.info("boxcartsave : " + products_no);		
		vo.setMember_id(principal.getName());
		vo.setProducts_no(products_no);
		vo.setAmount(1);
		int count = 0 ;
		
		count = this.cartMapper.boxcart(vo);				
		if(count==1) {
		  log.info("성공" );
		  return "product/BoxList.tiles";
		}else {
		  log.info("실패" );
		  return "product/BoxList.tiles";
		}

	}
	     
	
}
