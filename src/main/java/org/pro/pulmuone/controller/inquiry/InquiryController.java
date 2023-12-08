package org.pro.pulmuone.controller.inquiry;

import java.sql.SQLException;
import java.util.ArrayList;

import org.pro.pulmuone.domain.PageDTO;
import org.pro.pulmuone.domain.faq.FaqDTO;
import org.pro.pulmuone.mapper.faq.FaqMapper;
import org.pro.pulmuone.service.faq.FaqService;
import org.pro.pulmuone.service.inquiry.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class InquiryController {
	/*
	/mypage/inquiry/list.do=servlets.inquiry.command.InquiryList
	/forum/inquiry/write.do=servlets.inquiry.command.InquiryWrite
	/forum/inquiry/upload.do=servlets.inquiry.command.InquiryImageUpload
	/mypage/inquiry/view.do=servlets.inquiry.command.InquiryView
	/mypage/inquiry/delete.do=servlets.inquiry.command.InquiryDelete
	 */
	@Autowired
	private InquiryService inquiryService;

	@GetMapping("/mypage/inquiry/list")
	public String inquiryList(
			@RequestParam(value = "category",  required = false, defaultValue = "1" ) int category
			, @RequestParam(value = "pageNo",  required = false, defaultValue = "1" ) int pageNo
			, @RequestParam(value = "searchKeyword",  required = false, defaultValue = "") String searchKeyword
			, Model model) {
		log.info("> inquiryList..");
		
		return "mypage/inquiry/list.tiles";
	}
	
	// 문의 하기
	@GetMapping("/forum/inquiry/write")
	public String inquiryWriteGet() {
		return "forum/inquiry/write.tiles";
	}
	
	@PostMapping("/forum/inquiry/write")
	public String inquiryWritePost() {
		
		
		return "redirect:/mypage/inquiry/list";
	}
	
}
