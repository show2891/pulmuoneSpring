package org.pro.pulmuone.controller.mypage.personal.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pro.pulmuone.domain.mypage.MoreDTO;
import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.personal.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/ajax/mypage/personal/*")
@Log4j
public class AddressAjaxController {

	@Autowired
	private AddressService addressService;
	
	@PutMapping(value = "/address/setBasic", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Boolean> setBasic(Authentication authentication, int addrNo) {
		log.warn("> AddressAjaxController setBasic()...");

		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		
		boolean isUpdated = this.addressService.setDefaultAddr(memberNo, addrNo);
		
		return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
	}

	@DeleteMapping(value = "/address", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Boolean> delete(int addrNo) {
		log.warn("> AddressAjaxController delete()...");
		
		boolean isDeleted = this.addressService.deleteAddress(addrNo);
		
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
	}
	
	@PostMapping(value = "/address/write", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Boolean> write(Authentication authentication, @RequestBody AddrBookDTO dto) {
		log.warn("> AddressAjaxController write()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		dto.setMemberNo(memberNo);
		
		if (dto.getMemo() == null) {
			dto.setMemo("");
		}
		
		boolean isWritten = this.addressService.writeAddress(dto);
		
		return new ResponseEntity<Boolean>(isWritten, HttpStatus.OK);
	}
	
	@PutMapping(value = "/address/modify/{addrNo}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Boolean> modify(Authentication authentication, @PathVariable("addrNo") int addrNo, @RequestBody AddrBookDTO dto) {
		log.warn("> AddressAjaxController modify()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		dto.setMemberNo(memberNo);
		dto.setAddrNo(addrNo);
		
		if (dto.getMemo() == null) {
			dto.setMemo("");
		}
		
		boolean isModified = this.addressService.modifyAddress(dto);
		
		return new ResponseEntity<Boolean>(isModified, HttpStatus.OK);
	}
	
	@GetMapping(value = "/address/more", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Map<String, Object>> more(Authentication authentication, Model model, MoreDTO search) {
		log.warn("> AddressAjaxController more()...");
		
		CurrentUser user = (CurrentUser) authentication.getPrincipal();
		int memberNo = user.getMember().getMemberNo();
		
		int pagingSize = search.getEndNum() - search.getStartNum() + 1;
		MoreDTO responseSearch = new MoreDTO(memberNo, search.getStartNum() + pagingSize, search.getEndNum() + pagingSize);

		List<AddrBookDTO> allAddrBookDtoList = this.addressService.getList(memberNo);
		List<AddrBookDTO> moreAddrBookDto = null;
		
		if (allAddrBookDtoList != null && allAddrBookDtoList.size() >= responseSearch.getEndNum()) {
			moreAddrBookDto = allAddrBookDtoList.subList(responseSearch.getStartNum()-1, responseSearch.getEndNum());
			
		} else if (allAddrBookDtoList != null && allAddrBookDtoList.size() < responseSearch.getEndNum()) {
			moreAddrBookDto = allAddrBookDtoList.subList(responseSearch.getStartNum()-1, allAddrBookDtoList.size());
			
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("data", moreAddrBookDto);
		result.put("search", responseSearch);
		
		return allAddrBookDtoList != null ? new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK)
									: new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
}
