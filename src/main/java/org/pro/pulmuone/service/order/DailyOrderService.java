package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;
import org.pro.pulmuone.domain.order.daily.DrkOrderDTO;

public interface DailyOrderService {
	
	List<DailyItemInfoDTO> selectItems(List<DailyOrderItemDTO> items);
	
	int drkOrderInsert(DrkOrderDTO drkOrderDTO);
	
	String getOrderName(int member_no);

}
