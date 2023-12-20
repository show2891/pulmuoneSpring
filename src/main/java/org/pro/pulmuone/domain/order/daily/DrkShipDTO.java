package org.pro.pulmuone.domain.order.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrkShipDTO {

	private int drk_order_no;
	private String drk_receiver;
	private String drk_tel;
	private String drk_zip_code;
	private String drk_addr;
	private String drk_addr_detail;
	private String drk_memo;
	private int drk_ship_loc;
	
}
