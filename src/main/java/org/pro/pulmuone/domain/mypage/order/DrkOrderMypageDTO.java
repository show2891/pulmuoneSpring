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
public class DrkOrderMypageDTO {
	
	private int drk_order_no;
	private String drk_order_name;
	private String fc_name;
	private String fc_tel;
	private String fc_phone;
	private int drk_status;
	private int total_count;
	private int drk_order_type;
	private Date drk_start_date;
	private int drk_pay_method;
	private int drk_price;
	
}
