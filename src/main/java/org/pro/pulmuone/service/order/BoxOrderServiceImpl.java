package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.order.BoxItemInfoDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderItemDTO;
import org.pro.pulmuone.mapper.order.BoxOrderMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoxOrderServiceImpl implements BoxOrderService {

	private BoxOrderMapper boxOrderMapper;
	
	@Override
	public List<BoxItemInfoDTO> selectItems(List<BoxOrderItemDTO> items) {
		log.info("> BoxOrderServiceImpl.selectItems...");
		return this.boxOrderMapper.selectItems(items);
	}
	
	

}
