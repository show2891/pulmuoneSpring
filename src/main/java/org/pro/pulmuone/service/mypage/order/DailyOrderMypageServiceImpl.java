package org.pro.pulmuone.service.mypage.order;

import java.util.List;

import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
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
	
}
