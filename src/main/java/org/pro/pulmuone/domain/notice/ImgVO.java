package org.pro.pulmuone.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgVO {
	private Boolean uploaded;
	private String url;
}
