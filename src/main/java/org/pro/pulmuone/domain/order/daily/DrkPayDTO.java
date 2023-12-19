package org.pro.pulmuone.domain.order.daily;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrkPayDTO {

	private int drk_pay_no;    
	private int drk_order_no;    
	private Date drk_pay_datetime;       
	private int drk_price;  
	private int drk_discount_price;   
	private int drk_final_price;   
	private int drk_pay_method;   
	private int pay_info_no;            
	private int drk_pay_status;
	
}
