package org.pro.pulmuone.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
	
	private int coupon_no;
	private String coupon_name;
	private double discount;
	private int delivery_type;
	private int duplication;
	private int use_month;
	private int min_price;
	private int max_discount;
	
	
}