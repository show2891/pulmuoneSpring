package org.pro.pulmuone.mapper.mypage.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;

@Mapper
public interface DailyOrderMypageMapper {

	DrkOrderMypageDTO selectDailyOrder(int member_no);

	List<DrkOrderMypageDTO> selectDrinkInfos(@Param("member_no") int member_no, @Param("drinkingType") String drinkingType);
	
}
