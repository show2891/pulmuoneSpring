package org.pro.pulmuone.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddrBookDTO {
	
	private int addr_no;
	private int member_no;
	private String addr_name;
	private String name;
	private String tel;
	private int dest;
	private String zip_code;
	private String addr;
	private String addr_detail;
	private String memo;
	private int default_addr;
	private int total_count;
	
}
