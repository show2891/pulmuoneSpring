package org.pro.pulmuone.controller.mypage.personal.info;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.personal.info.PInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/ajax/mypage/personal/*")
@Log4j
public class PInfoAjaxController {

	@Autowired
	private PInfoService pInfoService;
	
	@PostMapping(value = "/info", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE } )
	public ResponseEntity<Boolean> checkPwd(Authentication authentication, String pwd) {
		log.warn("> PInfoAjaxController checkPwd()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		String storedPwd = user.getMember().getPwd();
		System.out.println(pwd);
		boolean pwdIsMatch = this.pInfoService.checkPwd(storedPwd, pwd);
		
		return new ResponseEntity<Boolean>(pwdIsMatch, HttpStatus.OK);
							
	}	
	
	@PutMapping(value = "/info/detail", consumes = "application/json", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE } )
	public ResponseEntity<Boolean> modify(Authentication authentication, @RequestBody MemberDTO dto) {
		log.warn("> PInfoAjaxController modify()...");
		
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
		String memberId = user.getMember().getMemberId();

		log.info(dto);
		if (dto.getNewPassword().equals("")) {
			dto.setNewPassword(null);
		}
		log.info(dto);
		
		boolean modifyResult = this.pInfoService.modifyMemberInfo(memberId, dto);
		
		return modifyResult ? new ResponseEntity<Boolean>(modifyResult, HttpStatus.OK)
							: new ResponseEntity<Boolean>(modifyResult, HttpStatus.INTERNAL_SERVER_ERROR);
	}	

}
