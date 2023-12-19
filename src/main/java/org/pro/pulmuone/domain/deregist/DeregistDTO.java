package org.pro.pulmuone.domain.deregist;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeregistDTO {

	private int memberNo;
	private String deregType;
	private String deregReason;
	private Date deregDate;
	
}
