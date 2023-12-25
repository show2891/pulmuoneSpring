package org.pro.pulmuone.service.mypage.order;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderBillDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.DrkOrderMypageProductsDTO;
import org.pro.pulmuone.domain.order.daily.AcntInfoDTO;
import org.pro.pulmuone.domain.order.daily.DrkHistoryDTO;
import org.pro.pulmuone.domain.order.daily.DrkScheduleDTO;
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

	@Override
	public int changeDrkOrderName(int drk_order_no, String drk_order_name) {
		log.info("DailyOrderMypageServiceImpl.changeDrkOrderName()...");
		return dailyOrderMypageMapper.changeDrkOrderName(drk_order_no, drk_order_name);
	}

	@Override
	public List<DrkOrderBillDTO> selectDrinkBills(int member_no, String searchDate) {
		log.info("DailyOrderMypageServiceImpl.selectDrinkBills()...");
		
		int year = 0, month = 0;
		if (searchDate == null) {
			LocalDate today = LocalDate.now();
			year = today.getYear();
			month = today.getMonthValue();
		} else {
			year = Integer.parseInt(searchDate.substring(0, 4));
			month = Integer.parseInt(searchDate.substring(4));
		} // if
		
		return dailyOrderMypageMapper.selectDailyBills(member_no, year, month); 
	}

	@Override
	public DrkOrderBillDTO selectDrinkBill(int drk_order_no) {
		log.info("DailyOrderMypageServiceImpl.selectDrinkBill()...");
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		return dailyOrderMypageMapper.selectDailyBill(drk_order_no, year, month);
	}

	@Override
	public AcntInfoDTO selectAcntInfo(int pay_info_no) {
		log.info("DailyOrderMypageServiceImpl.selectAcntInfo()...");
		return dailyOrderMypageMapper.selectAcntInfo(pay_info_no);
	}

	@Override
	public int getNowPrice(int drk_order_no) {
		log.info("DailyOrderMypageServiceImpl.getNowPrice()...");
		return dailyOrderMypageMapper.getNowPrice(drk_order_no);
	}

	@Override
	public int stopOrder(int drk_order_no, Date drk_end_date) {
		log.info("DailyOrderMypageServiceImpl.stopOrder()...");
		dailyOrderMypageMapper.updateDrkOrderStatus(drk_order_no);
		dailyOrderMypageMapper.updateDrkScheduleEndDate(drk_order_no, drk_end_date);
		return dailyOrderMypageMapper.deleteDrkHistory(drk_order_no, drk_end_date);
	}

	@Override
	public List<DrkOrderMypageProductsDTO> selectDrkOrderMypageProducts(int drk_order_no) {
		log.info("DailyOrderMypageServiceImpl.stopOrder()...");
		return dailyOrderMypageMapper.selectDrkOrderMypageProducts(drk_order_no);
	}

	@Override
	public int updateDrkOrder(int drk_order_no, List<DrkScheduleDTO> drkNewScheduleList) {
		log.info("DailyOrderMypageServiceImpl.updateDrkOrder()...");
		int rowCnt = 0;
		
		// 기존 스케줄 가져오기
		List<DrkScheduleDTO> drkOldScheduleList = dailyOrderMypageMapper.selectDrkScheduleList(drk_order_no);
		
		Iterator<DrkScheduleDTO> ir = drkOldScheduleList.iterator();
		Iterator<DrkScheduleDTO> ir2 = drkNewScheduleList.iterator();
		DrkScheduleDTO oldSchedule = new DrkScheduleDTO();
		DrkScheduleDTO newSchedule = new DrkScheduleDTO();
		String productsNo = null;
		int drk_schedule_no = 0;
		String drk_start_date = null;
		int oldCnt[] = new int [5];
		int newCnt[] = new int [5];
		int stopCnt = 0;
		LocalDate drk_date = null;
		int prdCnt = 0;
		LocalDate end_date = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		DrkHistoryDTO drkHistoryDTO = null;
		while (ir.hasNext()) {
			oldSchedule = (DrkScheduleDTO) ir.next();
			productsNo = oldSchedule.getProducts_no();
			
			while (ir2.hasNext()) {
				newSchedule = (DrkScheduleDTO) ir2.next();
				newSchedule.setDrk_end_date(oldSchedule.getDrk_end_date());	 // 기존 스케줄의 end_date 가져오기
				
				if (productsNo.equals(newSchedule.getProducts_no())) {
					drk_schedule_no = oldSchedule.getDrk_schedule_no();
					drk_start_date = newSchedule.getDrk_start_date().substring(0, 10);
					
					oldCnt[0] = oldSchedule.getMon_cnt();
					oldCnt[1] = oldSchedule.getTue_cnt();
					oldCnt[2] = oldSchedule.getWed_cnt();
					oldCnt[3] = oldSchedule.getThu_cnt();
					oldCnt[4] = oldSchedule.getFri_cnt();
					newCnt[0] = newSchedule.getMon_cnt();
					newCnt[1] = newSchedule.getTue_cnt();
					newCnt[2] = newSchedule.getWed_cnt();
					newCnt[3] = newSchedule.getThu_cnt();
					
					// 비교
					for (int i = 0; i < 5; i++) {
						// 카운드 수가 다르다면?
						log.info(oldCnt[i] + " / " + newCnt[i]);
						if(oldCnt[i] != newCnt[i]) {
							if (stopCnt == 0) {	// drk_schedule 수정 작업 한 번만 하기
								stopCnt = dailyOrderMypageMapper.stopDrkSchedule(drk_schedule_no, drk_start_date); 	// 기존 스케줄의 end_date 변경
								dailyOrderMypageMapper.insertDrkSchedule(newSchedule);			// 새 스케줄 insert
							} // if

							dailyOrderMypageMapper.changeDrkHistory(newSchedule);	// 기존 drk_history 삭제
							
							// 새 drk_history 추가
							end_date = oldSchedule.getDrk_end_date().toLocalDate();
							drk_date = LocalDate.parse(newSchedule.getDrk_start_date(), formatter);
							while (drk_date.isBefore(end_date) || drk_date.isEqual(end_date)) {		// start_date 부터 end_date까지 반복
								switch (i+1) {
								case 1:
									prdCnt = newSchedule.getMon_cnt();
									break;
								case 2:
									prdCnt = newSchedule.getTue_cnt();
									break;
								case 3:
									prdCnt = newSchedule.getWed_cnt();
									break;
								case 4:
									prdCnt = newSchedule.getThu_cnt();
									break;
								case 5:
									prdCnt = newSchedule.getFri_cnt();
									break;
								case 6:			// 주말일 때는 빠져나가기
									drk_date = drk_date.plusDays(2);
									continue;
								default :
								} // switch
								
								if (prdCnt == 0) {
									drk_date = drk_date.plusDays(1);
									continue;	// 수량이 0일 때는 빠져나가기
								}
								
								drkHistoryDTO = DrkHistoryDTO.builder()
																			.drk_schedule_no(newSchedule.getDrk_schedule_no())
																			.drk_order_no(drk_order_no)
																			.products_no(productsNo)
																			.products_cnt(prdCnt)
																			.drk_complete(0)
																			.drk_date(Date.valueOf(drk_date))
																			.drk_day(i+1)
																			.build();
								dailyOrderMypageMapper.insertDrkHistory(drkHistoryDTO);		// 새 drk_history 추가
								
								drk_date = drk_date.plusDays(1);
							} // while
							rowCnt += dailyOrderMypageMapper.insertDrkChanges(newSchedule); 	// drk 변경 내역 추가
						} // if
					} // for
				} else {
					// 추가
					dailyOrderMypageMapper.insertDrkSchedule(newSchedule);
					// dailyOrderMypageMapper.insertDrkHistory(newSchedule);
					rowCnt += dailyOrderMypageMapper.insertDrkChanges(newSchedule);
				} // if
			} // while
		} // while
		return rowCnt;
	}
	
}
