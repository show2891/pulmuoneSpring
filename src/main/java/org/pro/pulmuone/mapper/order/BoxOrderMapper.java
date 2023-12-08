package org.pro.pulmuone.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.order.box.BoxItemInfoDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderItemDTO;

public interface BoxOrderMapper {
	
	List<BoxItemInfoDTO> selectItems(@Param("items") List<BoxOrderItemDTO> items);
	
}
