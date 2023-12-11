package org.pro.pulmuone.controller.faq;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pro.pulmuone.domain.PageDTO;
import org.pro.pulmuone.domain.faq.FaqDTO;
import org.pro.pulmuone.domain.notice.ImgVO;
import org.pro.pulmuone.service.faq.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/forum/faq/*")
@Log4j
public class FaqController {
	/*
	/forum/faq/write.do=servlets.faq.command.FaqWrite
	/forum/faq/upload.do=servlets.faq.command.FaqImageUpload
	/forum/faq/delete.do=servlets.faq.command.FaqDelete
	 */
	@Autowired
	private FaqService faqService;

	@GetMapping("list")
	public String faqList(
			@RequestParam(value = "category",  required = false, defaultValue = "1" ) int category
			, @RequestParam(value = "pageNo",  required = false, defaultValue = "1" ) int pageNo
			, @RequestParam(value = "searchKeyword",  required = false, defaultValue = "") String searchKeyword
			, Model model) {
		log.info("> FaqList.process..");
		//@RequestParam("category") String category, @RequestParam("pageNo") String pageNo

		int numberPerPage = 5; // 페이지당 글 수
		int totalPages = 0; // 총 페이지수
		int total = 0;
		int numberOfPageBlock = 5;
		
		PageDTO paging = null;
		
		ArrayList<FaqDTO> faqList = null;

		System.out.println(pageNo); 
		System.out.println(category);
		System.out.println(numberPerPage);
		System.out.println(searchKeyword);

		try {
			faqList = this.faqService.getFaqList(pageNo, category, numberPerPage,searchKeyword);
			totalPages = this.faqService.getFaqTotalPage(numberPerPage, category, searchKeyword);
			total = this.faqService.getFaqTotal(category, searchKeyword);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		paging = new PageDTO(pageNo, numberPerPage, numberOfPageBlock, totalPages);

		model.addAttribute("list", faqList);
		model.addAttribute("total", total);
		model.addAttribute("paging", paging);


		return "forum/faq/list.tiles";
	}
	
	// faq 쓰기
	@GetMapping("write")
	public String faqWriteGet() {
		
		return "forum/faq/write.tiles";
	}
	
	@PostMapping("write")
	public String faqWritePost(FaqDTO dto, RedirectAttributes rttr) {
		
		int insertCount = 0;
		
		dto.setQ_no(0);
		
		try {
			insertCount = faqService.write(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if( insertCount != 1 ) {
			rttr.addAttribute("result", "failed");
			return "redirect:write";
		}
		rttr.addAttribute("result", "writed");
		
		return "redirect:list";
	}
	
	
	// 업로드
	@PostMapping("upload")
	public @ResponseBody ImgVO faqUpload(
			@RequestParam("upload") CommonsMultipartFile multipartFile
			, HttpServletRequest request
			, HttpServletResponse response
			) throws IllegalStateException, IOException {
		log.info("> faqUpload.. ");
		
		String saveDirectory = null;
		String filesystemName = null;
		
		ImgVO imgVo = new ImgVO();
		
		if( multipartFile != null ) {
			saveDirectory = request.getServletContext().getRealPath("/upload/faq/");
			
			File f = new File(saveDirectory);
		  	if( !f.exists() ) f.mkdirs();
		  	System.out.println(">>> saveDirectory : " + saveDirectory);
			
		  	String OriginalFilename = multipartFile.getOriginalFilename();
		  	filesystemName = getFileNameCheck(saveDirectory, OriginalFilename);
		  	
		  	File dest = new File(saveDirectory, filesystemName);
		  	multipartFile.transferTo(dest);
		  	
		  	imgVo.setUploaded(true);
		  	imgVo.setUrl("/upload/faq/" + filesystemName);
		  	
		  	System.out.println(imgVo);
		  	return imgVo;
		}
		imgVo.setUploaded(false);
		return imgVo;
	}
	
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;      
		while( true ) {         
			File f = new File(uploadRealPath, originalFilename);         
			if( !f.exists() ) return originalFilename;         
			// upload 폴더에 originalFilename 파일이 존재한다는 의미         a.txt (4자리)
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
			String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
			// asdfasf-3.txt
			originalFilename = fileName+"-"+(index)+ext;

			index++;
		} // while 
	}
}
