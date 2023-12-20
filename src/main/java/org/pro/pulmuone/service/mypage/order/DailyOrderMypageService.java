package org.pro.pulmuone.service.mypage.order;

import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
import org.pro.pulmuone.domain.order.daily.DrkShipDTO;

public interface DailyOrderMypageService {

	DrkOrderMypageDTO selectDailyOrder(int member_no);

	List<DrkOrderMypageDTO> selectDrinkInfos(int member_no, String drinkingType);
	List<DrkOrderMypageDTO> selectDrinkInfos(int member_no);

	DrkOrderMypageDTO selectDrinkInfo(int drk_order_no);

	DrkShipDTO selectDrinkShip(int drk_order_no);

	List<List<BoxOrderMypageProductsDTO>> selectNextWeekDrink(int drk_order_no);

	
}
