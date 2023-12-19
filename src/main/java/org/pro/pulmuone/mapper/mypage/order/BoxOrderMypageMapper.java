package org.pro.pulmuone.mapper.mypage.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;

@Mapper
public interface BoxOrderMypageMapper {

	List<BoxOrderMypageDTO> selectBoxOrder(int member_no);

	int getBoxOrderStatus(@Param("member_no") int member_no, @Param("status") int status);
	
	
}
