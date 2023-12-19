package org.pro.pulmuone.service.mypage.order;

import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;

public interface BoxOrderMypageService {

	List<BoxOrderMypageDTO> selectBoxOrder(int member_no);

	List<Integer> getBoxOrderStatus(int member_no);
	
	
	
}
