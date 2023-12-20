package org.pro.pulmuone.service.mypage.order;

import java.util.ArrayList;
import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
import org.pro.pulmuone.domain.order.daily.DrkShipDTO;
import org.pro.pulmuone.mapper.mypage.order.DailyOrderMypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class DailyOrderMypageServiceImpl implements DailyOrderMypageService {

	@Autowired
	private DailyOrderMypageMapper dailyOrderMypageMapper;

	@Override
	public DrkOrderMypageDTO selectDailyOrder(int member_no) {
		log.info("DailyOrderMypageServiceImpl.selectDailyOrder()...");
		return dailyOrderMypageMapper.selectDailyOrder(member_no);
	}

	@Override
	public List<DrkOrderMypageDTO> selectDrinkInfos(int member_no, String drinkingType) {
		log.info("DailyOrderMypageServiceImpl.selectDrinkInfos()...");
		return dailyOrderMypageMapper.selectDrinkInfos(member_no, drinkingType);
	}

	@Override
	public List<DrkOrderMypageDTO> selectDrinkInfos(int member_no) {
		log.info("DailyOrderMypageServiceImpl.selectDrinkInfos()...");
		return dailyOrderMypageMapper.selectDrinks(member_no);
	}

	@Override
	public DrkOrderMypageDTO selectDrinkInfo(int drk_order_no) {
		log.info("DailyOrderMypageServiceImpl.selectDrinkInfo()...");
		return dailyOrderMypageMapper.selectDrinkInfo(drk_order_no);
	}

	@Override
	public DrkShipDTO selectDrinkShip(int drk_order_no) {
		log.info("DailyOrderMypageServiceImpl.selectDrinkShip()...");
		return dailyOrderMypageMapper.selectDrinkShip(drk_order_no);
	}

	@Override
	public List<List<BoxOrderMypageProductsDTO>> selectNextWeekDrink(int drk_order_no) {
		log.info("DailyOrderMypageServiceImpl.selectNextWeekDrink()...");
		List<List<BoxOrderMypageProductsDTO>> list = new ArrayList<>();
		List<BoxOrderMypageProductsDTO> products = null;
		for (int i = 0; i < 5; i++) {
			products = dailyOrderMypageMapper.selectNextWeekDrink(drk_order_no, i);
			list.add(products);
		} // for
		return list;
	}
	
}
