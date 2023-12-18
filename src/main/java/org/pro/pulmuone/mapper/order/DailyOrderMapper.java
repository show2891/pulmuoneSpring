package org.pro.pulmuone.mapper.order;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

@Mapper
public interface DailyOrderMapper {
	
	List<DailyItemInfoDTO> selectItems(@Param("items") List<DailyOrderItemDTO> items, @Param("member_no") int member_no);

	String getOrderName(int member_no);
	
	int drkOrderInsert(@Param("drkOrderDTO") DrkOrderDTO drkOrderDTO);

	int cardInfoInsert(@Param("cardInfoDTO") CardInfoDTO cardInfoDTO);

	int acntInfoInsert(@Param("acntInfoDTO") AcntInfoDTO acntInfoDTO);

	int drkPayInsert(DrkPayDTO drkPayDTO);

	int drkShipInsert(DrkShipDTO drkShipDTO);

	int drkScheduleInsert(@Param("drkScheduleDTO") DrkScheduleDTO drkScheduleDTO
								, @Param("drkOrderNo") int drkOrderNo
								, @Param("today") Date today
								, @Param("drk_start_date") Date drk_start_date
								, @Param("drk_end_date") Date drk_end_date);

	int drkHistoryInsert(DrkHistoryDTO dto);

	int insertAddrBook(AddrBookDTO addr);
	
}
