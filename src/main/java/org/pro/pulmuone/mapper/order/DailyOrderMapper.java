package org.pro.pulmuone.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;

@Mapper
public interface DailyOrderMapper {
	
	List<DailyItemInfoDTO> selectItems(@Param("items") List<DailyOrderItemDTO> items);
	
}