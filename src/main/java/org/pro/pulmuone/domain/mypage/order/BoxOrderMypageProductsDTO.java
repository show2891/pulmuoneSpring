package org.pro.pulmuone.domain.mypage.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxOrderMypageProductsDTO {
	
	private String products_no;
	private String products_name;
	private String products_size;
	private String products_tag;
	private String products_type;
	private int price;
	private int event_price;
	private int products_cnt;
	private String img_path;
	private String system_name;
	
}
