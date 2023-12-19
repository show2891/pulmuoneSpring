package org.pro.pulmuone.service.order;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.pro.pulmuone.domain.order.AddrBookDTO;
import org.pro.pulmuone.domain.order.daily.AcntInfoDTO;
import org.pro.pulmuone.domain.order.daily.CardInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;
import org.pro.pulmuone.domain.order.daily.DrkHistoryDTO;
import org.pro.pulmuone.domain.order.daily.DrkOrderDTO;
import org.pro.pulmuone.domain.order.daily.DrkPayDTO;
import org.pro.pulmuone.domain.order.daily.DrkScheduleDTO;
import org.pro.pulmuone.domain.order.daily.DrkShipDTO;
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
	public List<DailyItemInfoDTO> selectItems(List<DailyOrderItemDTO> items, int member_no) {
		log.info("> DailyOrderServiceImpl.selectItems...");
		return this.dailyOrderMapper.selectItems(items, member_no);
	}

	@Override
	public int drkOrderInsert(DrkOrderDTO drkOrderDTO, int member_no, Date today, String drk_order_name) {
		log.info("> DailyOrderServiceImpl.drkOrderInsert...");
		drkOrderDTO.setMember_no(member_no);
		drkOrderDTO.setDrk_order_date(today);
		drkOrderDTO.setDrk_order_type(0);
		drkOrderDTO.setDrk_status(0);
		drkOrderDTO.setDrk_order_name(drk_order_name);
		int rowCnt = this.dailyOrderMapper.drkOrderInsert(drkOrderDTO);
		return rowCnt;
	}

	@Override
	public String getOrderName(int member_no) {
		log.info("> DailyOrderServiceImpl.getOrderName...");
		return this.dailyOrderMapper.getOrderName(member_no);
	}

	@Override
	public int cardInfoInsert(CardInfoDTO cardInfoDTO) {
		log.info("> DailyOrderServiceImpl.cardInfoInsert...");
		return this.dailyOrderMapper.cardInfoInsert(cardInfoDTO);
	}

	@Override
	public int acntInfoInsert(AcntInfoDTO acntInfoDTO) {
		log.info("> DailyOrderServiceImpl.acntInfoInsert...");
		return this.dailyOrderMapper.acntInfoInsert(acntInfoDTO);
	}

	@Override
	public int drkPayInsert(DrkPayDTO drkPayDTO, int drkOrderNo) {
		log.info("> DailyOrderServiceImpl.drkPayInsert...");
		drkPayDTO.setDrk_order_no(drkOrderNo);
		drkPayDTO.setDrk_pay_status(0);
		drkPayDTO.setDrk_discount_price(0);
		return this.dailyOrderMapper.drkPayInsert(drkPayDTO);
	}

	@Override
	public int drkShipInsert(DrkShipDTO drkShipDTO) {
		log.info("> DailyOrderServiceImpl.drkShipInsert...");
		return this.dailyOrderMapper.drkShipInsert(drkShipDTO);
	}

	@Override
	public int drkSchedulesInsert(List<DrkScheduleDTO> drkScheduleList, int drkOrderNo, Date today, Date drk_start_date, Date drk_end_date) {
		log.info("> DailyOrderServiceImpl.drkScheduleInsert...");
		int rowCnt = 0;
		
		Iterator<DrkScheduleDTO> ir = drkScheduleList.iterator();
		while (ir.hasNext()) {
			DrkScheduleDTO dto = (DrkScheduleDTO) ir.next();
			rowCnt += this.dailyOrderMapper.drkScheduleInsert(dto, drkOrderNo, today, drk_start_date, drk_end_date);
		} // while
		
		return rowCnt;
	}

	@Override
	public int drkHistoryInsert(List<DrkHistoryDTO> drkHistoryList) {
		log.info("> DailyOrderServiceImpl.drkHistoryInsert...");
		int rowCnt = 0;
		
		Iterator<DrkHistoryDTO> ir = drkHistoryList.iterator();
		while (ir.hasNext()) {
			DrkHistoryDTO dto = (DrkHistoryDTO) ir.next();
			rowCnt += this.dailyOrderMapper.drkHistoryInsert(dto);
		} // while
		
		return rowCnt;
	}

	@Override
	public int insertAddrBook(AddrBookDTO addr) {
		log.info("> DailyOrderServiceImpl.insertAddrBook...");
		return this.dailyOrderMapper.insertAddrBook(addr);
	}


}
