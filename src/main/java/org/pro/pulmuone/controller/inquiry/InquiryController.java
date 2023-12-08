package org.pro.pulmuone.controller.inquiry;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pro.pulmuone.domain.inquiry.InquiryDTO;
import org.pro.pulmuone.domain.notice.ImgVO;
import org.pro.pulmuone.service.inquiry.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class InquiryController {
	/*
	/mypage/inquiry/list.do=servlets.inquiry.command.InquiryList
	/forum/inquiry/write.do=servlets.inquiry.command.InquiryWrite
	/mypage/inquiry/view.do=servlets.inquiry.command.InquiryView
	/mypage/inquiry/delete.do=servlets.inquiry.command.InquiryDelete
	 */
	@Autowired
	private InquiryService inquiryService;

	// 문의하기 목록 
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
		log.info("> inquiryWriteGet..");
		return "forum/inquiry/write.tiles";
	}
	
	@PostMapping("/forum/inquiry/write")
	public String inquiryWritePost(Principal principal, InquiryDTO dto, RedirectAttributes rttr) {
		log.info("> inquiryWritePost..");
		
		dto.setMember_id( principal.getName() );
		dto.setInquiry_no(0);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>");
		System.out.println(dto);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>");
		int insertRow = 0;
		
		try {
			insertRow = inquiryService.write(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if( insertRow != 1 ) {
			rttr.addAttribute("result", "failed");
			return "redirect:/forum/inquiry/write";				
		}
		
		return "redirect:/mypage/inquiry/list";
	}
	
	// 업로드
	@PostMapping("/forum/inquiry/upload")
	public @ResponseBody ImgVO inquiryUpload(
			@RequestParam("upload") CommonsMultipartFile multipartFile
			, HttpServletRequest request
			, HttpServletResponse response) throws IllegalStateException, IOException {
		log.info("> inquiryUpload.. ");
		
		String saveDirectory = null;
		String filesystemName = null;
		
		ImgVO imgVo = new ImgVO();
		
		if( multipartFile != null ) {
			saveDirectory = request.getServletContext().getRealPath("/upload/inquiry/");
			
			File f = new File(saveDirectory);
		  	if( !f.exists() ) f.mkdirs();
		  	System.out.println(">>> saveDirectory : " + saveDirectory);
			
		  	String OriginalFilename = multipartFile.getOriginalFilename();
		  	filesystemName = getFileNameCheck(saveDirectory, OriginalFilename);
		  	
		  	File dest = new File(saveDirectory, filesystemName);
		  	multipartFile.transferTo(dest);
		  	
		  	imgVo.setUploaded(true);
		  	imgVo.setUrl("/upload/inquiry/" + filesystemName);
		  	
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
