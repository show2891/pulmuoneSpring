package org.pro.pulmuone.service.order;

import java.sql.Date;
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

public interface DailyOrderService {
	
	List<DailyItemInfoDTO> selectItems(List<DailyOrderItemDTO> items, int member_no);
	
	int drkOrderInsert(DrkOrderDTO drkOrderDTO, int member_no, Date today, String drk_order_name);
	
	String getOrderName(int member_no);

	int cardInfoInsert(CardInfoDTO cardInfoDTO);

	int acntInfoInsert(AcntInfoDTO acntInfoDTO);

	int drkPayInsert(DrkPayDTO drkPayDTO, int drkOrderNo);

	int drkShipInsert(DrkShipDTO drkShipDTO);

	int drkSchedulesInsert(List<DrkScheduleDTO> drkScheduleList, int drkOrderNo, Date today, Date drk_start_date, Date drk_end_date);

	int drkHistoryInsert(List<DrkHistoryDTO> drkHistoryList);

	int insertAddrBook(AddrBookDTO addr);

}
