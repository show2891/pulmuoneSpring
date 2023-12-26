package org.pro.pulmuone.controller.mypage.drink.payMethod;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pro.pulmuone.domain.mypage.MoreDTO;
import org.pro.pulmuone.domain.mypage.drink.payMethod.PayMethodDTO;
import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.drink.payMethod.PayMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/ajax/mypage/drink/*")
@Log4j
public class PayMethodAjaxController {

	@Autowired
	private PayMethodService payMethodService;
	
	@GetMapping("/paymethod/more")
	public ResponseEntity<Map<String, Object>> more(Authentication authentication, Model model, MoreDTO search) {
		log.warn("> PayMethodAjaxController more()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		
		int pagingSize = search.getEndNum() - search.getStartNum() + 1;
		MoreDTO responseSearch = new MoreDTO(memberNo, search.getStartNum() + pagingSize, search.getEndNum() + pagingSize);

		List<PayMethodDTO> allPayMethodDtoList = this.payMethodService.getList(memberNo);
		List<PayMethodDTO> payMethodDtoList = null;
		
		if (allPayMethodDtoList != null && allPayMethodDtoList.size() >= responseSearch.getEndNum()) {
			payMethodDtoList = allPayMethodDtoList.subList(responseSearch.getStartNum()-1, responseSearch.getEndNum());
			
		} else if (allPayMethodDtoList != null && allPayMethodDtoList.size() < responseSearch.getEndNum()) {
			payMethodDtoList = allPayMethodDtoList.subList(responseSearch.getStartNum()-1, allPayMethodDtoList.size());
			
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("data", payMethodDtoList);
		result.put("search", responseSearch);
		
		return allPayMethodDtoList != null ? new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK)
									: new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	@PutMapping("/paymethod/modify")
	public ResponseEntity<Boolean> modify(Authentication authentication, @RequestBody PayMethodDTO dto) {
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		
		dto.setMemberNo(memberNo);
		
		boolean isModified = this.payMethodService.modifyPayMethod(dto);
		
		return new ResponseEntity<Boolean>(isModified, HttpStatus.OK);
	}
	
}
