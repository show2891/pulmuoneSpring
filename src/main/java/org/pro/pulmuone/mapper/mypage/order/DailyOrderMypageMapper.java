package org.pro.pulmuone.mapper.mypage.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
import org.pro.pulmuone.domain.order.daily.DrkShipDTO;

@Mapper
public interface DailyOrderMypageMapper {

	DrkOrderMypageDTO selectDailyOrder(int member_no);

	List<DrkOrderMypageDTO> selectDrinkInfos(@Param("member_no") int member_no, @Param("drinkingType") String drinkingType);
	List<DrkOrderMypageDTO> selectDrinks(int member_no);

	DrkOrderMypageDTO selectDrinkInfo(int drk_order_no);

	DrkShipDTO selectDrinkShip(int drk_order_no);

	List<BoxOrderMypageProductsDTO> selectNextWeekDrink(@Param("drk_order_no") int drk_order_no, @Param("plusDay") int plusDay);

}
