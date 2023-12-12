package org.pro.pulmuone.domain.notice;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
	/*
	NOTICE_NO NOT NULL NUMBER       
	TITLE     NOT NULL VARCHAR2(90) 
	CONTENT   NOT NULL CLOB         
	REGDATE   NOT NULL DATE  
	 */
	private	int notice_no; 
	private String title;
	private String content;
	private Date regdate;
	private String filesrc;
	
	private CommonsMultipartFile file;	// 필드 추가
}

