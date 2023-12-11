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
	private int duplication;
	private int min_price;
	private int max_discount;
	
}
