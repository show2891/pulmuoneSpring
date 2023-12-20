package org.pro.pulmuone.service.mypage.order;

import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageListDTO;
import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.box.BoxPayDTO;
import org.pro.pulmuone.domain.order.box.BoxShipDTO;

public interface BoxOrderMypageService {

	List<BoxOrderMypageDTO> selectBoxOrder(int member_no);

	List<Integer> getBoxOrderStatus(int member_no);

	List<BoxOrderMypageListDTO> selectBoxInfos(int member_no, String startSearchDate, String endSearchDate);

	BoxOrderMypageListDTO selectBoxInfo(int box_order_no);

	BoxPayDTO selectBoxPay(int box_order_no);

	int updateHaveCoupon(int box_order_no);

	int updateBoxOrder(int box_order_no);

	int updateBoxPay(int box_pay_no);

	BoxShipDTO selectBoxShip(int box_order_no);

	List<CouponDTO> selectUsedCouponList(int box_pay_no);
	
}
