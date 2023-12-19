package org.pro.pulmuone.service.mypage.order;

import java.util.ArrayList;
import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.mapper.mypage.order.BoxOrderMypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoxOrderMypageServiceImpl implements BoxOrderMypageService {
	
	@Autowired
	private BoxOrderMypageMapper boxOrderMypageMapper;
	
	
	@Override
	public List<BoxOrderMypageDTO> selectBoxOrder(int member_no) {
		log.info("BoxOrderMypageServiceImpl.selectBoxOrder()...");
		return boxOrderMypageMapper.selectBoxOrder(member_no);
	}


	@Override
	public List<Integer> getBoxOrderStatus(int member_no) {
		log.info("BoxOrderMypageServiceImpl.getBoxOrderStatus()...");
		List<Integer> boxOrderStatus = new ArrayList<>();
		
		int result = 0;
		for (int i = 1; i <= 4; i++) {
			result = boxOrderMypageMapper.getBoxOrderStatus(member_no, i);
			boxOrderStatus.add(result);
		}
		
		return boxOrderStatus;
	}

	
	

}
