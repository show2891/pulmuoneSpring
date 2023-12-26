package org.pro.pulmuone.domain.order.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardInfoDTO {

	private int card_info_no;
	private int drk_order_no;
	private int member_no;
	private String card_num;
	private String expiry_date;
	private String birth_date;
	
}
