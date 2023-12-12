package org.pro.pulmuone.domain.order.daily;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyOrderItemDTO {

	private String itemCode;
	private List<Integer> dayQty;
	
}
