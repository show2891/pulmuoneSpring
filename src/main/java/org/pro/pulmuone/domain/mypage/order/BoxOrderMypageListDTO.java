package org.pro.pulmuone.domain.mypage.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxOrderMypageListDTO {
	
	private int box_order_no;
	private String box_order_date;
	private String box_order_status;
	private int total_count;
	
	private List<BoxOrderMypageProductsDTO> boxOrderProductsList;
	
}
