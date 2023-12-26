package org.pro.pulmuone.controller.order;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.pro.pulmuone.domain.order.AddrBookDTO;
import org.pro.pulmuone.domain.order.FranchiseDTO;
import org.pro.pulmuone.domain.order.OrderAddrBookDTO;
import org.pro.pulmuone.domain.order.daily.AcntInfoDTO;
import org.pro.pulmuone.domain.order.daily.CardInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;
import org.pro.pulmuone.domain.order.daily.DailyOrderItemDTO;
import org.pro.pulmuone.domain.order.daily.DrkHistoryDTO;
import org.pro.pulmuone.domain.order.daily.DrkOrderDTO;
import org.pro.pulmuone.domain.order.daily.DrkPayDTO;
import org.pro.pulmuone.domain.order.daily.DrkScheduleDTO;
import org.pro.pulmuone.domain.order.daily.DrkShipDTO;
import org.pro.pulmuone.service.order.DailyOrderServiceImpl;
import org.pro.pulmuone.service.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/daily/order/*")
@Log4j
public class DailyOrderController {

	@Autowired
	private DailyOrderServiceImpl dailyOrderServiceImpl;

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	

	@GetMapping("step1")
	public String step1(@RequestParam(name = "item") String itemsStr , Model model, Principal principal) {
		log.info("> DailyOrderController.step1 ...");
		
		// 1. 사용자 정보 출력
		String username = principal.getName();
						
		// 사용자 정보 전달
		OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		model.addAttribute("member", member);
						
		// 2. member_no 가져오기
		int member_no = member.getMember_no();
				        
        // member 넘겨주기
		model.addAttribute("member", member);
		
		// 1. 파라미터로 넘어온 상품 정보 출력
		ObjectMapper objectMapper = new ObjectMapper();

		// 파라미터로 넘어온 json String 변환
		// {"item":[{"itemCode":"0071654","dayQty":[1,0,1,0,1]}]} -> [{"itemCode":"0071654","dayQty":[1,0,1,0,1]}]
		itemsStr = itemsStr.substring(8, itemsStr.length());

		try {
			// 장바구니 정보 json -> 객체로 가져오기
			List<DailyOrderItemDTO> items = Arrays.asList(objectMapper.readValue(itemsStr, DailyOrderItemDTO[].class));

			// 상품 정보 가져오기
			List<DailyItemInfoDTO> itemInfos = dailyOrderServiceImpl.selectItems(items, member_no);

			// 주문 갯수 담기
			Iterator<DailyOrderItemDTO> ir = items.iterator();
			int index = 0;
			while (ir.hasNext()) {
				DailyOrderItemDTO item = (DailyOrderItemDTO) ir.next();
				itemInfos.get(index).setProducts_cnt(item.getDayQty());
				index++;
			} // while

			// 상품 정보 전달
			model.addAttribute("itemInfos", itemInfos);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} // try

		return "order/daily/step1.tiles";
	}
	
	
	@PostMapping("step2")
 	public String step2(Model model, FranchiseDTO franchiseDTO, DrkOrderDTO drkOrderDTO, DrkScheduleDTO drkScheduleDTO, String drk_start_date
 							, DrkShipDTO drkShipDTO, String saveAddrChk, DrkPayDTO drkPayDTO, CardInfoDTO cardInfoDTO, AcntInfoDTO acntInfoDTO, Principal principal) {
		log.info("> BoxOrderController.step2 ...");
		
		// 1. 사용자 정보 가져오기
		String username = principal.getName();
						
		// 사용자 정보 전달
		OrderAddrBookDTO member = orderServiceImpl.getMemberInfo(username);
		model.addAttribute("member", member);
						
		// 2. member_no 가져오기
		int member_no = member.getMember_no();
				        
		// 2. drk_order 테이블 insert
		// 음용 name 구해오기
		String drk_order_name = dailyOrderServiceImpl.getOrderName(member_no);
		
		// drkOrderDTO에 정보 저장
		Date today = Date.valueOf(LocalDate.now());
		dailyOrderServiceImpl.drkOrderInsert(drkOrderDTO, member_no, today, drk_order_name);
		
		
		// 3. drkOrderNo 저장
		int drkOrderNo = drkOrderDTO.getDrk_order_no();
		
		
		// 4. 결제 상세 정보 저장
		int payMethod = drkPayDTO.getDrk_pay_method();
		
		if (payMethod == 0) {
			cardInfoDTO.setMember_no(member_no);
			cardInfoDTO.setDrk_order_no(drkOrderNo);
			dailyOrderServiceImpl.cardInfoInsert(cardInfoDTO);
			drkPayDTO.setPay_info_no(cardInfoDTO.getCard_info_no());
		} else if (payMethod == 1) {
			acntInfoDTO.setMember_no(member_no);
			acntInfoDTO.setDrk_order_no(drkOrderNo);
			dailyOrderServiceImpl.acntInfoInsert(acntInfoDTO);
			drkPayDTO.setPay_info_no(acntInfoDTO.getAcnt_info_no());
		} else {
			System.out.println("현장결제");
		} // if
		
		
		// 5. 결제 정보 저장
		dailyOrderServiceImpl.drkPayInsert(drkPayDTO, drkOrderNo);
		
		
		// 6. 배송지 정보 저장
		drkShipDTO.setDrk_order_no(drkOrderNo);
		dailyOrderServiceImpl.drkShipInsert(drkShipDTO);
		
		// 7. 만약 saveAddrChk 체크 돼있으면 주소록에 저장
		if(saveAddrChk!=null && saveAddrChk.equals("on")) {
			AddrBookDTO addr = new AddrBookDTO().builder()
																			.addr(drkShipDTO.getDrk_addr())
																			.addr_detail(drkShipDTO.getDrk_addr_detail())
																			.addr_name(drkShipDTO.getDrk_receiver())
																			.default_addr(0)
																			.dest(drkShipDTO.getDrk_ship_loc())
																			.member_no(member_no)
																			.memo(drkShipDTO.getDrk_memo())
																			.name(drkShipDTO.getDrk_receiver())
																			.tel(drkShipDTO.getDrk_tel())
																			.zip_code(drkShipDTO.getDrk_zip_code())
																		.build();
			this.dailyOrderServiceImpl.insertAddrBook(addr);
		};
		
		// 8. 음용 시작일, 종료일 구하기
		LocalDate start_date = getStartDate(drk_start_date);
		LocalDate end_date = start_date.plusMonths(2).withDayOfMonth(1).minusDays(1);
		
		
		// 9. 스케줄 정보 저장, drk_chedule_no 가져오기
		List<DrkScheduleDTO> drkScheduleList = drkScheduleDTO.getDrkScheduleList();
		dailyOrderServiceImpl.drkSchedulesInsert(drkScheduleList, drkOrderNo, today, Date.valueOf(start_date), Date.valueOf(end_date));

		
		// 10. 음용 기록 저장
		DrkHistoryDTO drkHistoryDTO = null;
		List<DrkHistoryDTO> drkHistoryList = new ArrayList<DrkHistoryDTO>();
		
		LocalDate drk_date = null;
		int drk_dayOfWeek = 0, prdCnt = 0, drkScheduleNo = 0;
		String productsNo = null;
		
		Iterator<DrkScheduleDTO> ir = drkScheduleList.iterator();
		
		while (ir.hasNext()) {		// 여러 상품 주문 시 반복
			DrkScheduleDTO drkSchedule = (DrkScheduleDTO) ir.next();
			drkScheduleNo = drkSchedule.getDrk_schedule_no();
			productsNo = drkSchedule.getProducts_no();

			drk_date = start_date;
			while (drk_date.isBefore(end_date) || drk_date.isEqual(end_date)) {		// start_date 부터 end_date까지 반복
				
				drk_dayOfWeek = drk_date.getDayOfWeek().getValue();		// 숫자 요일
				switch (drk_dayOfWeek) {
				case 1:
					prdCnt = drkSchedule.getMon_cnt();
					break;
				case 2:
					prdCnt = drkSchedule.getTue_cnt();
					break;
				case 3:
					prdCnt = drkSchedule.getWed_cnt();
					break;
				case 4:
					prdCnt = drkSchedule.getThu_cnt();
					break;
				case 5:
					prdCnt = drkSchedule.getFri_cnt();
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
															.drk_schedule_no(drkScheduleNo)
															.drk_order_no(drkOrderNo)
															.products_no(productsNo)
															.products_cnt(prdCnt)
															.drk_complete(0)
															.drk_date(Date.valueOf(drk_date))
															.drk_day(drk_dayOfWeek)
															.build();
				drkHistoryList.add(drkHistoryDTO);
				
				drk_date = drk_date.plusDays(1);
			} // while
		} // while
		
		dailyOrderServiceImpl.drkHistoryInsert(drkHistoryList);

		
		// 11. 가맹점 정보, 음용 시작일, 메모 전달
		model.addAttribute("franchiseDTO", franchiseDTO);
		model.addAttribute("memo", drkShipDTO.getDrk_memo());
		model.addAttribute("drk_start_date", drk_start_date);
		
		
		// 12. 주문한 상품 정보 가져와서 전달
		List<DailyOrderItemDTO> items = getItems(drkScheduleList);
		List<DailyItemInfoDTO> dailyItemInfoList = dailyOrderServiceImpl.selectItems(items, member_no);
		Iterator<DailyItemInfoDTO> ir2 = dailyItemInfoList.iterator();
		int i = 0;
		while (ir2.hasNext()) {
			DailyItemInfoDTO dto = (DailyItemInfoDTO) ir2.next();
			dto.setProducts_cnt(items.get(i).getDayQty());
			i++;
		} // while
		model.addAttribute("dailyItemInfoList", dailyItemInfoList);
		
		return "order/daily/step2.tiles";
	}


	private List<DailyOrderItemDTO> getItems(List<DrkScheduleDTO> drkScheduleList) {
		List<DailyOrderItemDTO> items = new ArrayList<>();
		DailyOrderItemDTO item = null;
		List<Integer> dayQty = null;
		
		// drkScheduleList 정보를 items에 담기
		Iterator<DrkScheduleDTO> ir = drkScheduleList.iterator();
		while (ir.hasNext()) {
			DrkScheduleDTO dto = (DrkScheduleDTO) ir.next();
			
			dayQty = new ArrayList<>();
			dayQty.add(dto.getMon_cnt());
			dayQty.add(dto.getTue_cnt());
			dayQty.add(dto.getWed_cnt());
			dayQty.add(dto.getThu_cnt());
			dayQty.add(dto.getFri_cnt());
			
			item = new DailyOrderItemDTO(dto.getProducts_no(), dayQty);
			items.add(item);
		} // while
		
		return items;
	}


	private LocalDate getStartDate(String drk_start_date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd");
		LocalDate start_date = LocalDate.parse(drk_start_date, formatter);
		
		int dayOfWeek = start_date.getDayOfWeek().getValue();
		
		// 음용 시작일이 주말이라면 평일로 바꾸기
		if (dayOfWeek == 6) {
			System.out.println(start_date.toString() + "는 토요일입니다.");
			start_date = start_date.plusDays(2);
			dayOfWeek = 1;
		} else if (dayOfWeek == 7) {
			System.out.println(start_date.toString() + "는 일요일입니다.");
			start_date = start_date.plusDays(1);
			dayOfWeek = 1;
		} // if
		
		return start_date;
	} // getStartDate

}
