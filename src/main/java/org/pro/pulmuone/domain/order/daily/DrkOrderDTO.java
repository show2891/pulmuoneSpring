package org.pro.pulmuone.domain.order.daily;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrkOrderDTO {

	private int drk_order_no;
	private int member_no;
	private String drk_order_name;
	private String drk_start_date;
	private int drk_order_type;
	private Date drk_order_date;
	private String fc_no;
	private int drk_status;
	
}
