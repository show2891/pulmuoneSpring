package org.pro.pulmuone.domain.mypage.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxOrderMypageDTO {
	
	private int box_order_no;
	private String products_name;
	private String box_final_price;
	private String box_order_date;
	private String box_order_status;
	private int total_count;
	
}
