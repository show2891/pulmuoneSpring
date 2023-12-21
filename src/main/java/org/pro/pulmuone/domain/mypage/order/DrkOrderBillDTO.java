package org.pro.pulmuone.domain.mypage.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrkOrderBillDTO {
	
	private int drk_order_no;
	private String drk_order_name;
	private int drk_month_price;
	private int drk_pay_method;
	private int pay_info_no;
	private String fc_name;
	private String fc_tel;
	private String fc_phone;
	
}
