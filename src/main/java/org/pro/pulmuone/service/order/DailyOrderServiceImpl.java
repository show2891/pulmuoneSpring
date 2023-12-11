package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;
import org.pro.pulmuone.mapper.order.DailyOrderMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class DailyOrderServiceImpl implements DailyOrderService {

	private DailyOrderMapper dailyOrderMapper;
	
	@Override
	public List<DailyItemInfoDTO> selectItems(List<DailyOrderItemDTO> items) {
		log.info("> DailyOrderServiceImpl.selectItems...");
		return this.dailyOrderMapper.selectItems(items);
	}
	
	

}
