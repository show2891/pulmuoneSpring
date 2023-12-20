package org.pro.pulmuone.mapper.mypage.order;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageListDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.box.BoxPayDTO;
import org.pro.pulmuone.domain.order.box.BoxShipDTO;

@Mapper
public interface BoxOrderMypageMapper {

	List<BoxOrderMypageDTO> selectBoxOrder(int member_no);

	int getBoxOrderStatus(@Param("member_no") int member_no, @Param("status") int status);

	List<BoxOrderMypageListDTO> selectBoxInfos(@Param("member_no") int member_no, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	List<BoxOrderMypageProductsDTO> selectBoxOrderMypageProducts(int box_order_no);

	BoxOrderMypageListDTO selectBoxInfo(int box_order_no);

	BoxPayDTO selectBoxPay(int box_order_no);

	int updateHaveCoupon(int box_pay_no);

	int updateBoxOrder(int box_order_no);

	int updateBoxPay(int box_pay_no);

	BoxShipDTO selectBoxShip(int box_order_no);

	List<CouponDTO> selectUsedCouponList(int box_pay_no);
	
}
