package org.pro.pulmuone.controller.cart;


import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.mapper.cart.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cart/*")
public class CartController {

  /*
   * /cart/daily.do = servlets.cart.command.CartDaily /cart/box.do = servlets.cart.command.CartBox
   */

  @Autowired
  private CartMapper cartMapper;

  // 매일 장바구니, 키즈

  @RequestMapping("daily")
  public String daily(CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {

	log.info("> DailyCart Start");
	vo.setMember_id(principal.getName());

	List<CartVO> list = this.cartMapper.daily(vo);

	model.addAttribute("list", list);
	return "cart/daily.tiles";
  }

  // 택배 장바구니	
  @RequestMapping("box")
  public String box(CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {

	log.info("> BoxCart Start");
	vo.setMember_id(principal.getName());

	List<CartVO> list = this.cartMapper.box(vo);
	model.addAttribute("list", list);
	return "cart/box.tiles";
  }

  @RequestMapping("kid/save")
  public String kidcartsave(@RequestParam(value = "products_no", required = false) String products_no, CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {
	log.info("kidcartsave : " + products_no);
	int count = 0;
	String[] products_nos = products_no.split(",");
	vo.setMember_id(principal.getName());
	if (products_nos[0].equals("0072840") && products_nos[1].equals("0072976")) {
	  vo.setMon_cnt(1);
	  vo.setTue_cnt(0);
	  vo.setWed_cnt(1);
	  vo.setThu_cnt(0);
	  vo.setFir_cnt(1);
	  vo.setProducts_no(products_nos[0]);
	  this.cartMapper.dailycartdelete(vo);
	  count = this.cartMapper.dailycart(vo);
	  vo.setMon_cnt(0);
	  vo.setTue_cnt(1);
	  vo.setWed_cnt(0);
	  vo.setThu_cnt(1);
	  vo.setFir_cnt(0);
	  vo.setProducts_no(products_nos[1]);
	  this.cartMapper.dailycartdelete(vo);
	  count = this.cartMapper.dailycart(vo);

	}

	if (count == 1) {
	  log.info("성공");
	  return "product/DailyList.tiles";
	} else {
	  log.info("실패");
	  return "product/DailyList.tiles";
	}
  }

  @RequestMapping("daily/save")
  public String dailycartsave(@RequestParam(value = "products_no", required = false) String products_no
	  ,@RequestParam(name = "item") String itemsStr
	  , CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {
	log.warn("dailycartsave : " + products_no);
	log.warn("itemsStr : " + itemsStr);
	int count = 0;
	String[] products_nos = products_no.split(",");
	vo.setMember_id(principal.getName());
	  String[] itemsStrs = itemsStr.split(",");
	  int[] intArray = new int[itemsStrs.length];
	  for(int i = 0; i < itemsStrs.length; i++){
	    intArray[i] = Integer.parseInt(itemsStrs[i]);
	}
		vo.setMon_cnt(intArray[0]);
		vo.setTue_cnt(intArray[1]);
		vo.setWed_cnt(intArray[2]);
		vo.setThu_cnt(intArray[3]);
		vo.setFir_cnt(intArray[4]);	
	
	for (int i = 0; i < products_nos.length; i++) {
	  vo.setProducts_no(products_nos[i]);
	  this.cartMapper.dailycartdelete(vo);
	  count = this.cartMapper.dailycart(vo);
	}

	if (count == 1) {
	  log.info("성공");
	  return "product/DailyList.tiles";
	} else {
	  log.info("실패");
	  return "product/DailyList.tiles";
	}
  }

  @RequestMapping("box/save")
  public String boxcartsave(@RequestParam(value = "products_no", required = false) String products_no, CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {
	log.info("boxcartsave : " + products_no);
	int count = 0;
	vo.setMember_id(principal.getName());
	vo.setAmount(1);
	String[] products_nos = products_no.split(",");
	for (int i = 0; i < products_nos.length; i++) {
	  vo.setProducts_no(products_nos[i]);
	  this.cartMapper.boxcartdelete(vo);
	  count = this.cartMapper.boxcart(vo);
	}

	if (count == 1) {
	  log.info("성공");
	  return "product/BoxList.tiles";
	} else {
	  log.info("실패");
	  return "product/BoxList.tiles";
	}

  }

  @RequestMapping("daily/delete")
  public String dailycartdelete(@RequestParam(value = "products_no", required = false) String products_no, CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {

	log.info("> dailycartdelete Start" + products_no);
	vo.setMember_id(principal.getName());
	vo.setProducts_no(products_no);
	this.cartMapper.dailycartdelete(vo);

	return "cart/daily.tiles";
  }

  // 택배 장바구니	삭제
  @RequestMapping("box/delete")
  public String boxcartdelete(@RequestParam(value = "products_no", required = false) String products_no, CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {

	log.info("> boxcartdelete Start" + products_no);
	vo.setMember_id(principal.getName());
	vo.setProducts_no(products_no);
	this.cartMapper.boxcartdelete(vo);
	return "cart/box.tiles";
  }

  @RequestMapping("daily/update")
  public String dailycartupdate(@RequestParam(value = "products_no", required = false) String products_no, @RequestParam(value = "idx", required = false) String idx, CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {

	log.info("> dailycartupdate Start");
	int count = 0;
	String[] idxs = idx.split(",");
	  vo.setMon_cnt(Integer.parseInt(idxs[0]));
	  vo.setTue_cnt(Integer.parseInt(idxs[1]));
	  vo.setWed_cnt(Integer.parseInt(idxs[2]));
	  vo.setThu_cnt(Integer.parseInt(idxs[3]));
	  vo.setFir_cnt(Integer.parseInt(idxs[4]));	
	  vo.setMember_id(principal.getName());
	  vo.setProducts_no(products_no);
	count = this.cartMapper.dailycartupdate(vo);

	if (count == 1) {
	  log.info("성공");
	  return "cart/daily.tiles";
	} else {
	  log.info("실패");
	  return "cart/daily.tiles";
	}
  }
  
  @RequestMapping("box/update")
  public String boxcartupdate(@RequestParam(value = "products_no", required = false) String products_no, @RequestParam(value = "idx", required = false) String idx, CartVO vo, Model model, Principal principal) throws ClassNotFoundException, SQLException {

	log.info("> boxcartupdate Start");
	int count = 0;	
	  vo.setAmount(Integer.parseInt(idx));
	  vo.setMember_id(principal.getName());
	  vo.setProducts_no(products_no);
	count = this.cartMapper.boxcartupdate(vo);

	if (count == 1) {
	  log.info("성공");
	  return "cart/box.tiles";
	} else {
	  log.info("실패");
	  return "cart/box.tiles";
	}

  }

}
