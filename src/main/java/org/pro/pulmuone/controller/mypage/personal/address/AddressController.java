package org.pro.pulmuone.controller.mypage.personal.address;

import java.util.List;

import org.pro.pulmuone.domain.mypage.MoreDTO;
import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.personal.address.AddressService;
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
@RequestMapping("/mypage/personal/*")
@Log4j
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = "/address", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public String list(Authentication authentication, Model model) {
		log.warn("> AddressController list()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		
		int startNum = 1;
		int pagingSize = 3;
		int endNum = startNum + pagingSize - 1;
		
		List<AddrBookDTO> allAddrBookDtoList = this.addressService.getList(memberNo);
		int dtoCount = allAddrBookDtoList.size();

		List<AddrBookDTO> addrBookDtoList;
		if (dtoCount >= endNum) {
			addrBookDtoList = allAddrBookDtoList.subList(startNum - 1, endNum); 
		} else if(dtoCount == 0) {
			addrBookDtoList = null; 
		} else {
			addrBookDtoList = allAddrBookDtoList.subList(startNum - 1, dtoCount); 
		}
		
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(addrBookDtoList);
        
        MoreDTO moreDto = new MoreDTO(memberNo, startNum, endNum); 
        String pagingInfo = gson.toJson(moreDto);
        
		model.addAttribute("dtoList", json);
		model.addAttribute("dtoCount", dtoCount);
		model.addAttribute("memberNo", memberNo);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "mypage/personal/address/list.tiles";
	}
	
	@GetMapping("/address/write")
	public String write() {
		log.warn("> AddressController write()...");
		
		return "mypage/personal/address/write.tiles";
	}
	
	@GetMapping("/address/{addrNo}")
	public String modify(Authentication authentication, Model model, @PathVariable("addrNo") int addrNo) {
		log.warn("> AddressController modify()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		String memberId = user.getMember().getMemberId();
		
		AddrBookDTO dto = this.addressService.getAddress(addrNo); 

		model.addAttribute("dto", dto);
		model.addAttribute("memberId", memberId);
		
		
		return "mypage/personal/address/write.tiles";
	}
	
}
