package org.pro.pulmuone.domain.event;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventWinnerVO {

	private int win_board_no;
	private String win_title;
	private String win_content;
	private Date win_regdate;
	
}
