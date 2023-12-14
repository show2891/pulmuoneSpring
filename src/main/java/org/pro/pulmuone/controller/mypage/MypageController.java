package org.pro.pulmuone.controller.mypage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/*")
@Log4j
public class MypageController {

	@GetMapping("mypage")
	public String summary(HttpServletRequest request) {
		log.warn("> MypageController mypage()...");
			
		return "mypage/home/userSummmary.tiles";
	}
	

	
}
