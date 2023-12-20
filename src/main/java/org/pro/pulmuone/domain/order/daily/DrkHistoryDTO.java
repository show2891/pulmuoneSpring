package org.pro.pulmuone.domain.order.daily;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrkHistoryDTO {

	private int drk_history_no;
	private int drk_schedule_no;
	private int drk_order_no;
	private String products_no;
	private int products_cnt;
	private Date drk_date;
	private int drk_day;
	private int drk_complete;
	
}
