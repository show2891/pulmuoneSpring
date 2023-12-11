package org.pro.pulmuone.domain.taste;

import lombok.Data;

@Data
public class TasteDTO {
	
	   private int gift_no;         // 세트ID
	   private String gift_name;      // 세트명
	   private String gift_description;      //  내용
	   private String mon_products_no;      // 월
	   private String tue_products_no;      // 화
	   private String wed_products_no;      // 수
	   private String thu_products_no;      // 목	   
	   private String fri_products_no;         // 금
	   private int gift_original_price;      // 가격
	   
	   private int products_tag; 
	   private String products_name;
	   private String system_name;         // 이미지
}	   