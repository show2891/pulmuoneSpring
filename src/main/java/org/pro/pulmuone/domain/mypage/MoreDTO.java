package org.pro.pulmuone.domain.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoreDTO {
	
	private int memberNo;
	private int startNum;
	private int endNum;
}
