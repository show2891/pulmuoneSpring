package org.pro.pulmuone.domain.order.daily;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrkScheduleDTO {

	private int drk_schedule_no;
	private int drk_order_no;
	private Date reg_date;
	private String products_no;
	private int mon_cnt;
	private int tue_cnt;
	private int wed_cnt;
	private int thu_cnt;
	private int fri_cnt;
	private String drk_start_date;
	private Date drk_end_date;
	
	private List<DrkScheduleDTO> drkScheduleList;
	
}
