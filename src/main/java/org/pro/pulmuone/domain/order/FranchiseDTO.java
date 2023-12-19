package org.pro.pulmuone.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchiseDTO {
	
	private String fc_no;            
	private String fc_name;        
	private String fc_addr;        
	private double fc_lat;         
	private double fc_lng;         
	private String fc_phone;
	private String fc_tel;         
	private int fc_type;           
	private int gift_avail;       
	
}
