package org.pro.pulmuone.domain.order.box;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxOrderItemDTO {
	
	private String itemCode;
	private int qty;
	private int eventIdx;
	
}
