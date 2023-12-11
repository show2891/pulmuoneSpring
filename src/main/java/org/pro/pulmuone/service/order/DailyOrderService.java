package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;

public interface DailyOrderService {
	
	List<DailyItemInfoDTO> selectItems(List<DailyOrderItemDTO> items);

}
