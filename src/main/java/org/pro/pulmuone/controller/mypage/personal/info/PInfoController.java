package org.pro.pulmuone.controller.mypage.personal.info;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.personal.info.PInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mypage/personal/*")
@Log4j
public class PInfoController {

	@Autowired
	private PInfoService pInfoService; 
	
	@GetMapping("/info")
	public String info() {
		log.warn("> PInfoController info()...");
		
		return "mypage/personal/info/checkPwd.tiles";
	}
	
	@GetMapping("/info/detail")
	public String detail(Authentication authentication, Model model) {
		log.warn("> PInfoController detail()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		String memberId = user.getMember().getMemberId();
		
		MemberDTO dto = this.pInfoService.getMemberInfo(memberId);
		String emailId = dto.getEmail().substring(0, dto.getEmail().indexOf("@"));
		String emailHost = dto.getEmail().substring(dto.getEmail().indexOf("@"));
		
		model.addAttribute("dto", dto);
		model.addAttribute("emailId", emailId);
		model.addAttribute("emailHost", emailHost);
		
		return "mypage/personal/info/detail.tiles";
	}
	
}
