package org.pro.pulmuone.controller.member;

import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String login(HttpServletRequest request) {
		log.warn("> MemberController login()...");
		
		// referer - 이전 경로를 가지고 있는 속성
		String referer = request.getRequestURI();
		String queryString = null;
		queryString = request.getQueryString();
		
		if ( queryString != null ) {
			referer += "?" + queryString;
		}
		request.getSession().setAttribute("referer", referer);
		
		return "member/login.tiles";
	}
	
	@GetMapping("regist/step1")
	public String registStep1() {
		
		return "member/regist/step1.tiles";
	}

	@GetMapping("regist/step2")
	public String interceptStep2() {

		return "member/regist/step1.tiles";
	}
	
	@PostMapping("regist/step2")
	public String registStep2(Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String rrnBirthDate = request.getParameter("rrnBirthDate");
		String rrnGenderCode = request.getParameter("rrnGenderCode");		
		
		boolean isAuthorized = this.memberService.authorizeNICE(name, tel, rrnBirthDate, rrnGenderCode);
		
		if (isAuthorized) {
			// 이미 등록된 회원
			return "/member/login";
			
		} else {
			model.addAttribute("name", name);
			model.addAttribute("tel", tel);
			model.addAttribute("rrnBirthDate", rrnBirthDate);
			model.addAttribute("rrnGenderCode", rrnGenderCode);
			
			return "member/regist/step2.tiles";
		}		
	}
	
	@GetMapping("regist/step3")
	public String interceptStep3() {

		return "member/regist/step1.tiles";
	}
	
	@PostMapping("regist/step3")
	public String registStep3(Model model, HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String rrnBirthDate = request.getParameter("rrnBirthDate");
		String rrnGenderCode = request.getParameter("rrnGenderCode");
		
		String memberId = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		String email = request.getParameter("email");
		int adApproval = ( request.getParameter("agree3") != null ? 1 : 0 );
		int marketingApproval = ( request.getParameter("agree4") != null ? 1 : 0 );
		
		
		model.addAttribute("name", name);
		model.addAttribute("tel", tel);
		model.addAttribute("rrnBirthDate", rrnBirthDate);
		model.addAttribute("rrnGenderCode", rrnGenderCode);
		model.addAttribute("memberId", memberId);
		model.addAttribute("pwd", pwd);
		model.addAttribute("email", email);
		model.addAttribute("adApproval", adApproval);
		model.addAttribute("marketingApproval", marketingApproval);
		
		return "member/regist/step3.tiles";
	}
	
	
	@GetMapping("regist/step4")
	public String interceptStep4() {
		// TODO 페이지 완성하면 GET은 step1 리턴하도록 수정
		return "member/regist/step1.tiles";
	}
	
	@PostMapping("regist/step4")
	public String registStep4(Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		String name = request.getParameter("name");
		
		String tel = request.getParameter("tel");
		String rrnBirthDate = request.getParameter("rrnBirthDate");
		String rrnGenderCode = request.getParameter("rrnGenderCode");
		Date birthDate = this.memberService.getBirthDate(rrnBirthDate, rrnGenderCode);
		
		String memberId = request.getParameter("memberId");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		int adApproval = Integer.parseInt(request.getParameter("adApproval"));
		int marketingApproval = Integer.parseInt(request.getParameter("marketingApproval"));
		
		String invCode = request.getParameter("friendCode");
		String invMemberId = request.getParameter("friendId");
		
		MemberDTO dto = MemberDTO.builder()
									.memberId(memberId)
									.pwd(pwd)
									.name(name)
									.tel(tel)
									.email(email)
									.birthDate(birthDate)
									.adApproval(adApproval)
									.marketingApproval(marketingApproval) 
									.build();
								
//		request.setAttribute("dto", dto);
		
		int signUpResult = this.memberService.signUp(dto);

//		request.setAttribute("memberId", memberId);
//		request.setAttribute("pwd", pwd);
		
		return "member/regist/step4.tiles";
	}
}
