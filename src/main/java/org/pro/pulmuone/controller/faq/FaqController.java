package org.pro.pulmuone.controller.faq;

import org.pro.pulmuone.mapper.faq.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/forum/faq/*")
@Log4j
public class FaqController {
	/*
	 /forum/faq/list.do=servlets.faq.command.FaqList
	/forum/faq/write.do=servlets.faq.command.FaqWrite
	/forum/faq/upload.do=servlets.faq.command.FaqImageUpload
	/forum/faq/view.do=servlets.faq.command.FaqView
	/forum/faq/edit.do=servlets.faq.command.FaqEdit
	/forum/faq/delete.do=servlets.faq.command.FaqDelete
	 */
	@Autowired
	private FaqMapper faqMapper;

	@GetMapping("list")
	public String faqList(
			@RequestParam( value = "category", defaultValue = "1") int category
			, @RequestParam( value = "pageNo", defaultValue = "1") int pageNo
			, @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword
			, Model model) {
		log.info("> FaqList.process..");
		//@RequestParam("category") String category, @RequestParam("pageNo") String pageNo

		System.out.println(category);

		/*
		 * int numberPerPage = 5; // 페이지당 글 수 int totalPages = 0; // 총 페이지수 int total =
		 * 0; int numberOfPageBlock = 5;
		 * 
		 * PageDTO paging = null;
		 * 
		 * // 검색 if( category != 1 && searchKeyword == "" ) { return
		 * "redirect:/forum/faq/list"; }
		 * 
		 * ArrayList<FaqDTO> faqList = null;
		 * 
		 * System.out.println(pageNo); System.out.println(category);
		 * System.out.println(numberPerPage); System.out.println(searchKeyword);
		 * 
		 * try { faqList = faqMapper.select(pageNo, category, numberPerPage);
		 * //totalPages = faqMapper.getTotalPages(numberPerPage,category,
		 * searchKeyword); //total = faqMapper.selectCount(category,searchKeyword); }
		 * catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * 
		 * paging = new PageDTO(pageNo, numberPerPage, numberOfPageBlock, totalPages);
		 * 
		 * model.addAttribute("list", faqList); model.addAttribute("total", total);
		 * model.addAttribute("paging", paging);
		 */

		return "forum/faq/list.tiles";
	}
}
