package org.pro.pulmuone.controller.mypage.drink.payMethod;

import java.util.List;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.domain.mypage.BankDTO;
import org.pro.pulmuone.domain.mypage.MoreDTO;
import org.pro.pulmuone.domain.mypage.drink.payMethod.PayMethodDTO;
import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.drink.payMethod.PayMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mypage/drink/*")
@Log4j
public class PayMethodController {

	@Autowired
	private PayMethodService payMethodService;
	
	@GetMapping(value = "/paymethod", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE } )
	public String list(Authentication authentication, Model model) {
		log.warn("PayMethodController list()... ");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		
		int startNum = 1;
		int pagingSize = 1;
		int endNum = startNum + pagingSize - 1;
		
		List<PayMethodDTO> allPayMethodDtoList = this.payMethodService.getList(memberNo);
		int dtoCount = allPayMethodDtoList.size();
		
		List<PayMethodDTO> payMethodDtoList;
		if (dtoCount >= endNum) {
			payMethodDtoList = allPayMethodDtoList.subList(startNum - 1, endNum); 			
		} else if(dtoCount == 0) {
			payMethodDtoList = null; 
		} else {
			payMethodDtoList = allPayMethodDtoList.subList(startNum - 1, dtoCount); 
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(payMethodDtoList);
		
        MoreDTO moreDto = new MoreDTO(memberNo, startNum, endNum); 
        String pagingInfo = gson.toJson(moreDto);
		

        
		model.addAttribute("dtoList", json);
		model.addAttribute("dtoCount", dtoCount);
		model.addAttribute("memberNo", memberNo);
		model.addAttribute("pagingInfo", pagingInfo);

		
		return "mypage/drink/paymethod/list.tiles";
	}
	
	@GetMapping("/paymethod/cust/{drkOrderNo}/{fcNo}")
	public String modify(Authentication authentication, Model model, @PathVariable("drkOrderNo") long drkOrderNo, @PathVariable("fcNo") int fcNo) {
		log.warn("PayMethodController modify()... ");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		String memberId = user.getMember().getMemberId();
		String name = user.getMember().getName();
		
		MemberDTO memberDto = this.payMethodService.getMemberTel(memberId);
		String[] tel = memberDto.getTel().split("-");
		String phoneFirst = tel[0];
		String phoneLast = tel[2];
		
		PayMethodDTO dto = this.payMethodService.getPaymethod(drkOrderNo);
		List<BankDTO> allBankDtoList = this.payMethodService.getBankList();
		
		
		model.addAttribute("dto", dto);
		model.addAttribute("bankDtoList", allBankDtoList);
		model.addAttribute("phoneFirst", phoneFirst);
		model.addAttribute("phoneLast", phoneLast);
		model.addAttribute("name", name);
		
		return "mypage/drink/paymethod/modify.tiles";
	}
}
