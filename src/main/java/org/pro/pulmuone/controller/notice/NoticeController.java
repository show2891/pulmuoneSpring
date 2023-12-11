package org.pro.pulmuone.controller.notice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pro.pulmuone.domain.PageDTO;
import org.pro.pulmuone.domain.notice.ImgVO;
import org.pro.pulmuone.domain.notice.NoticeDTO;
import org.pro.pulmuone.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/forum/notice/*")
@Log4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	// 공지사항 목록 
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
	
	// 공지사항 상세보기 
	@GetMapping("view")
	public String noticeView(Model model, @RequestParam("seq") int seq) {
		log.info("> noticeView..");
		
		NoticeDTO vo = null;
		
		try {
			vo = noticeService.getDetailView(seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("vo", vo);
		
		return "forum/notice/view.tiles";
	}
	
	// 공지사항 쓰기 
	@GetMapping("write")
	public String noticeWriteGet() {
		log.info("> noticeWriteGet..");
		return "forum/notice/write.tiles";
	}
	
	@PostMapping("write")
	public String noticeWritePost(NoticeDTO dto, RedirectAttributes rttr) {
		log.info("> noticeWritePost..");
		
		int insertCount = 0;
		try {
			insertCount = noticeService.write(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if( insertCount != 1 ) {
			rttr.addFlashAttribute("result", "failed");
			return "redirect:write";
		}
		rttr.addFlashAttribute("result","success");
		return "redirect:list"; 
	}

	// 공지사항 수정 
	@GetMapping("edit")
	public String noticeEditGet(Model model, @RequestParam("seq") int seq) {
		log.info("> noticeEditGet..");
		
		NoticeDTO vo = null;
		try {
			vo = noticeService.getDetailView(seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("vo",vo);
		
		return "forum/notice/edit.tiles";
	}
	
	@PostMapping("edit")
	public String noticeEditPost(@RequestParam("seq") int seq, NoticeDTO dto, RedirectAttributes rttr) {
		log.info("> noticeEditPost..");
		
		System.out.println(dto.getNotice_no());
		
		dto.setNotice_no(seq);
		int updateCount = 0;
		try {
			updateCount = noticeService.edit(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if( updateCount != 1) {
			rttr.addFlashAttribute("result", "failed");
			return "redirect:edit";
		}
		
		rttr.addFlashAttribute("result", "modified");
		return "redirect:view?seq=" + dto.getNotice_no();
	}
	
	// 공지사항 삭제 
	@GetMapping("delete")
	public String noticeDelete(@RequestParam("seq") int seq, RedirectAttributes rttr) {
		log.info("> noticeDelete..");
		int deleteCount = 0;
		try {
			deleteCount = noticeService.delete(seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if( deleteCount != 1 ) {
			rttr.addFlashAttribute("result", "failed");
			return "redirect:delete";
		}
		rttr.addFlashAttribute("result", "deleted");
		return "redirect:list";
	}
	
	// 업로드
	@PostMapping("upload")
	public @ResponseBody ImgVO noticeUpload(
			@RequestParam("upload") CommonsMultipartFile multipartFile
			, HttpServletRequest request
			, HttpServletResponse response) throws IllegalStateException, IOException {
		log.info("> noticeUpload.. ");
		
		String saveDirectory = null;
		String filesystemName = null;
		
		ImgVO imgVo = new ImgVO();
		
		if( multipartFile != null ) {
			saveDirectory = request.getServletContext().getRealPath("/upload/notice/");
			
			File f = new File(saveDirectory);
		  	if( !f.exists() ) f.mkdirs();
		  	System.out.println(">>> saveDirectory : " + saveDirectory);
			
		  	String OriginalFilename = multipartFile.getOriginalFilename();
		  	filesystemName = getFileNameCheck(saveDirectory, OriginalFilename);
		  	
		  	File dest = new File(saveDirectory, filesystemName);
		  	multipartFile.transferTo(dest);
		  	
		  	imgVo.setUploaded(true);
		  	imgVo.setUrl("/upload/notice/" + filesystemName);
		  	
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
