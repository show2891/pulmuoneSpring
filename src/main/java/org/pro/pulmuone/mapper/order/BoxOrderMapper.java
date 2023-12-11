package org.pro.pulmuone.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.box.BoxItemInfoDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderItemDTO;

@Mapper
public interface BoxOrderMapper {
	
	List<BoxItemInfoDTO> selectItems(@Param("items") List<BoxOrderItemDTO> items);
	
	List<CouponDTO> getCouponList(@Param("member_no") int member_no, @Param("total_price") int total_price);
	
}
