package org.pro.pulmuone.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;
import org.pro.pulmuone.domain.order.daily.DrkOrderDTO;

@Mapper
public interface DailyOrderMapper {
	
	List<DailyItemInfoDTO> selectItems(@Param("items") List<DailyOrderItemDTO> items);

	String getOrderName(int member_no);
	
	int drkOrderInsert(@Param("drkOrderDTO") DrkOrderDTO drkOrderDTO);
	
}
