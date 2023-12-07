package org.pro.pulmuone.controller.order;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.pro.pulmuone.domain.order.BoxItemInfoDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderItemDTO;
import org.pro.pulmuone.service.order.BoxOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/order/box/*")
@Log4j
public class BoxOrderController {
	
	@Autowired
	private BoxOrderServiceImpl boxOrderServiceImpl;
	
	@GetMapping("step1")
	public String step1(@RequestParam(name = "item") String itemsStr , Model model) {
		log.info("> BoxOrderController.step1 ...");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// {"item":[{"itemCode":"0073561","qty":1}]} -> [{"itemCode":"0073561","qty":1}]
		itemsStr = itemsStr.substring(8, itemsStr.length()-1);
		
		try {
			// 장바구니 정보 json -> 객체로 가져오기
			List<BoxOrderItemDTO> items = Arrays.asList(objectMapper.readValue(itemsStr, BoxOrderItemDTO[].class));
			
			// 상품 정보 가져오기
			List<BoxItemInfoDTO> itemInfos = boxOrderServiceImpl.selectItems(items);
			
			// 주문 갯수 담기
			Iterator<BoxOrderItemDTO> ir = items.iterator();
			int index = 0;
			while (ir.hasNext()) {
				BoxOrderItemDTO item = (BoxOrderItemDTO) ir.next();
				itemInfos.get(index).setProducts_cnt(item.getQty());
				index++;
			} // while
			
			// 상품 정보 전달
			model.addAttribute("itemInfos", itemInfos);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} // try
		
		return "order/box/step1.tiles";
	}

}
