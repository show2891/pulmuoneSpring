package org.pro.pulmuone.controller.order;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.OrderAddrBookDTO;
import org.pro.pulmuone.domain.order.box.BoxItemInfoDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderItemDTO;
import org.pro.pulmuone.service.order.BoxOrderServiceImpl;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/box/order/*")
@Log4j
public class BoxOrderController {
	
	@Autowired
	private BoxOrderServiceImpl boxOrderServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	
	@GetMapping("step1")
	public String step1(@RequestParam(name = "item") String itemsStr, Model model) {
		log.info("> BoxOrderController.step1 ...");
		
		// 1. 파라미터로 넘어온 상품 정보 출력
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 파라미터로 넘어온 json String 변환
		// {"item":[{"itemCode":"0073561","qty":1}]} -> [{"itemCode":"0073561","qty":1}]
		itemsStr = itemsStr.substring(8, itemsStr.length()-1);
		
		List<BoxOrderItemDTO> items = null;
		List<BoxItemInfoDTO> itemInfos = null;
		int total_price = 0;
		try {
			// 장바구니 정보 json -> 객체로 가져오기
			items = Arrays.asList(objectMapper.readValue(itemsStr, BoxOrderItemDTO[].class));
			
			// 상품 정보 가져오기
			itemInfos = boxOrderServiceImpl.selectItems(items);
			
			// 주문 갯수 담기
			Iterator<BoxOrderItemDTO> ir = items.iterator();
			BoxOrderItemDTO item = null;
			BoxItemInfoDTO itemInfo = null;
			int index = 0;
			int qty = 0;
			while (ir.hasNext()) {
				item = (BoxOrderItemDTO) ir.next();
				qty = item.getQty();
				
				itemInfo = itemInfos.get(index);
				itemInfo.setProducts_cnt(qty);
				index++;
				total_price += itemInfo.getPrice() * qty;
			} // while
			
			// 상품 정보 전달
			model.addAttribute("itemInfos", itemInfos);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} // try
		

		// 2. 사용자 정보 출력
		// 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        // 사용자 id 가져오기
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } // if
        
        // 사용자 정보 전달
        OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		model.addAttribute("member", member);
		
		
		// 3. 쿠폰 리스트 전달
		List<CouponDTO> couponList = boxOrderServiceImpl.getCouponList(member.getMember_no(), total_price);
		model.addAttribute("couponList", couponList);
		
		
        
		return "order/box/step1.tiles";
	}

}
