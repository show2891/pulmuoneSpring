package org.pro.pulmuone.domain.mypage.personal.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddrBookDTO {

    private int addrNo;
	private int memberNo;
	private String addrName;
	private String name;
	private String tel;
	private int dest;
	private String zipCode;
	private String addr;
	private String addrDetail;
	private String memo;
	private int defaultAddr;
	
}
