package org.pro.pulmuone.controller.mypage.personal.address;

import java.util.List;

import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.security.domain.CurrentUser;
import org.pro.pulmuone.service.mypage.personal.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/mypage/personal/*")
@Log4j
public class AddressAjaxController {

	@Autowired
	private AddressService addressService;
	
	@PutMapping(value = "/address/setBasic", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public String setBasic(Authentication authentication, Model model) {
		

		
		return "mypage/personal/address/list.tiles";
	}

	@DeleteMapping(value = "/address", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Boolean> delete(Authentication authentication, Model model, int addrNo) {
		
		boolean isDeleted = this.addressService.deleteAddress(addrNo);
		
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
	}
	
}
