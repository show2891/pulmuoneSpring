package org.pro.pulmuone.domain.order.daily;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcntInfoDTO {

	private int acnt_info_no;
	private int drk_order_no;
	private int member_no;    
	private String acnt_holder;    
	private int bank_no;
	private String acnt_num;
	private String bank_name;
	
}
