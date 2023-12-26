package org.pro.pulmuone.domain.mypage.order;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrkOrderMypageProductsDTO {
	
	private String products_no;
	private String products_name;
	private String products_size;
	// private String products_tag;
	// private String products_type;
	private int price;
	private int mon_cnt;
	private int tue_cnt;
	private int wed_cnt;
	private int thu_cnt;
	private int fri_cnt;
	private String img_path;
	private String system_name;
	private Date drk_start_date;
	
}
