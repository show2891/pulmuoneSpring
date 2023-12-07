package org.pro.pulmuone.controller.notice;

import java.sql.SQLException;
import java.util.ArrayList;

import org.pro.pulmuone.domain.PageDTO;
import org.pro.pulmuone.domain.notice.NoticeDTO;
import org.pro.pulmuone.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/forum/notice/*")
@Log4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	/*
		/forum/notice/list.do=servlets.notice.command.NoticeList
		/forum/notice/write.do=servlets.notice.command.NoticeWrite
		/forum/notice/upload.do=servlets.notice.command.NoticeImageUpload
		/forum/notice/view.do=servlets.notice.command.NoticeView
		/forum/notice/edit.do=servlets.notice.command.NoticeEdit
		/forum/notice/delete.do=servlets.notice.command.NoticeDelete
	 */
	@GetMapping("list")
	public String noticeList(Model model, @RequestParam(value = "pageNo", required=false, defaultValue = "1") int pageNo) {
		log.info("> noticeList..");

		
		int numberPerPage = 10; // 페이지당 보여질 개수 
		int numberOfPageBlock = 5; // 페이지번호 갯수 
		int totalRows = 0;
		int totalPages = 1;

		ArrayList<NoticeDTO> list = null;
		
		try {
			list = noticeService.getNoticeList(pageNo, numberPerPage);
			totalRows = noticeService.getTotalRows();
			totalPages = noticeService.getTotalPages(numberPerPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		PageDTO paging = new PageDTO(pageNo, numberPerPage, numberOfPageBlock, totalPages);
		model.addAttribute("list", list);
		model.addAttribute("totalRows", totalRows);
		model.addAttribute("paging", paging);
		
		
		return "forum/notice/list.tiles";
	}
}
