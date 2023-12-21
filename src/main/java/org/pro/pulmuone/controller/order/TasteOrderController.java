package org.pro.pulmuone.controller.order;

import org.pro.pulmuone.domain.order.OrderAddrBookDTO;
import org.pro.pulmuone.service.order.DailyOrderService;
import org.pro.pulmuone.service.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/taste/order/*")
@Log4j
public class TasteOrderController {

	@Autowired
	private DailyOrderService dailyOrderService;

	@Autowired
	private OrderServiceImpl orderServiceImpl;


	@GetMapping("step1")
	public String step1(Model model) {
		log.info("> TasteOrderController.step1 ...");

		// 1. 사용자 정보 출력
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


		// 2. member_no 가져오기
		int member_no = member.getMember_no();

		return "order/taste/step1.tiles";
	}


}
