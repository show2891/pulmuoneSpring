package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.box.BoxItemInfoDTO;
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

	@Override
	public List<CouponDTO> getCouponList(int member_no, int total_price) {
		log.info("> BoxOrderServiceImpl.getCouponList... 회원번호 : " + member_no);
		return this.boxOrderMapper.getCouponList(member_no, total_price);
	}
	

}
