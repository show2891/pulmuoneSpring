package org.pro.pulmuone.controller.member;

import java.sql.SQLException;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/ajax/member/*")
@Log4j
public class AjaxMemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = "regist/duplicateIdCheck", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Boolean> duplicateIdCheck(String memberId) throws ClassNotFoundException, SQLException {
		log.warn("> AjaxMemberController duplicateIdCheck()...");

		boolean isDuplicated = this.memberService.duplicateIdCheck(memberId);
		
		return new ResponseEntity<Boolean>(isDuplicated, HttpStatus.OK);
	}
	
	@GetMapping(value = "regist/invCodeCheck", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<MemberDTO> invCodeCheck(String invCode) throws ClassNotFoundException, SQLException {
		log.warn("> AjaxMemberController invCodeCheck()...");
		
		MemberDTO dto = null;
		dto = this.memberService.invCodeCheck(invCode);

		if (dto == null) {
			dto = new  MemberDTO();
			dto.setMemberNo(0);			
		}
		
		return dto != null ? new ResponseEntity<MemberDTO>(dto, HttpStatus.OK)
							: new ResponseEntity<MemberDTO>(dto, HttpStatus.OK);
	}
	
	

}
