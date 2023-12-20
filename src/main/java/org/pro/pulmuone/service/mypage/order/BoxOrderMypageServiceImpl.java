package org.pro.pulmuone.service.mypage.order;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageListDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.order.CouponDTO;
import org.pro.pulmuone.domain.order.box.BoxPayDTO;
import org.pro.pulmuone.domain.order.box.BoxShipDTO;
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

	@Override
	public List<BoxOrderMypageListDTO> selectBoxInfos(int member_no, String startSearchDate, String endSearchDate) {
		log.info("BoxOrderMypageServiceImpl.selectBoxInfos()...");
		
		List<BoxOrderMypageListDTO> boxOrderMypageList = null;
		LocalDate localStartDate = null;
		LocalDate localEndDate = null;
		
		if (startSearchDate == null && endSearchDate == null) {
			localEndDate = LocalDate.now();
			localStartDate = localEndDate.minusMonths(3);
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			localStartDate = LocalDate.parse(startSearchDate, formatter);
			localEndDate = LocalDate.parse(endSearchDate, formatter);
		} // if
		
		Date startDate = Date.valueOf(localStartDate);
		Date endDate = Date.valueOf(localEndDate);
		
		boxOrderMypageList = boxOrderMypageMapper.selectBoxInfos(member_no, startDate, endDate);
		
		for (int i = 0; i < boxOrderMypageList.size(); i++) {
			List<BoxOrderMypageProductsDTO> boxOrderMypageProductsList = boxOrderMypageMapper.selectBoxOrderMypageProducts(boxOrderMypageList.get(i).getBox_order_no());
			boxOrderMypageList.get(i).setBoxOrderProductsList(boxOrderMypageProductsList);
		} // for
		
		return boxOrderMypageList;
	}

	@Override
	public BoxOrderMypageListDTO selectBoxInfo(int box_order_no) {
		log.info("BoxOrderMypageServiceImpl.selectBoxInfo()...");
		
		BoxOrderMypageListDTO boxOrderMypageListDTO = boxOrderMypageMapper.selectBoxInfo(box_order_no);
		List<BoxOrderMypageProductsDTO> boxOrderMypageProductsList = boxOrderMypageMapper.selectBoxOrderMypageProducts(box_order_no);
		boxOrderMypageListDTO.setBoxOrderProductsList(boxOrderMypageProductsList);
		
		return boxOrderMypageListDTO;
	}

	@Override
	public BoxPayDTO selectBoxPay(int box_order_no) {
		log.info("BoxOrderMypageServiceImpl.selectBoxPay()...");
		return boxOrderMypageMapper.selectBoxPay(box_order_no);
	}

	@Override
	public int updateHaveCoupon(int box_pay_no) {
		log.info("BoxOrderMypageServiceImpl.updateHaveCoupon()...");
		return boxOrderMypageMapper.updateHaveCoupon(box_pay_no);
	}

	@Override
	public int updateBoxOrder(int box_order_no) {
		log.info("BoxOrderMypageServiceImpl.updateBoxOrder()...");
		return boxOrderMypageMapper.updateBoxOrder(box_order_no);
	}

	@Override
	public int updateBoxPay(int box_pay_no) {
		log.info("BoxOrderMypageServiceImpl.updateBoxPay()...");
		return boxOrderMypageMapper.updateBoxPay(box_pay_no);
	}

	@Override
	public BoxShipDTO selectBoxShip(int box_order_no) {
		log.info("BoxOrderMypageServiceImpl.selectBoxShip()...");
		return boxOrderMypageMapper.selectBoxShip(box_order_no);
	}

	@Override
	public List<CouponDTO> selectUsedCouponList(int box_pay_no) {
		log.info("BoxOrderMypageServiceImpl.selectUsedCouponList()...");
		return boxOrderMypageMapper.selectUsedCouponList(box_pay_no);
	}

}
