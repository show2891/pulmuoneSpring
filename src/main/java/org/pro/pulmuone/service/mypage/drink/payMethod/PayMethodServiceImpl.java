package org.pro.pulmuone.service.mypage.drink.payMethod;

import java.util.List;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.domain.mypage.BankDTO;
import org.pro.pulmuone.domain.mypage.drink.payMethod.PayMethodDTO;
import org.pro.pulmuone.mapper.member.MemberMapper;
import org.pro.pulmuone.mapper.mypage.BankMapper;
import org.pro.pulmuone.mapper.mypage.drink.payMethod.PayMethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayMethodServiceImpl implements PayMethodService {

	@Autowired
	private PayMethodMapper payMethodMapper;
	
	@Autowired
	private BankMapper bankMapper; 
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<PayMethodDTO> getList(int memberNo) {
		
		List<PayMethodDTO> list = this.payMethodMapper.selectPayMethodList(memberNo);
		
		return list;
	}

	@Override
	public PayMethodDTO getPaymethod(long drkOrderNo) {
		
		PayMethodDTO dto = this.payMethodMapper.selectPayMethod(drkOrderNo);
		
		return dto;
	}

	@Override
	public List<BankDTO> getBankList() {
		
		List<BankDTO> list = this.bankMapper.getAllBankList();
		
		return list;
	}

	@Override
	public MemberDTO getMemberTel(String memberId) {
		
		MemberDTO dto = this.memberMapper.selectMemberForInfo(memberId);
		
		return dto;
	}
	
	@Override
	@Transactional
	public boolean modifyPayMethod(PayMethodDTO dto) {
		
		PayMethodDTO oldDto = this.payMethodMapper.selectPayMethod(dto.getDrkOrderNo());
		
		int deleteResult = 0;
		int insertResult = 0;
		int updateResult = 0;
		
		if (oldDto.getDrkPayMethod() == 2 && dto.getDrkPayMethod() != 2) {
			
			// drk_pay_method 유형으로 신규 정보 INSERT
			insertResult = this.payMethodMapper.insertPayInfoNo(dto);
			updateResult = this.payMethodMapper.updateDrkPay(dto);
			
			return insertResult + updateResult == 2 ? true : false;
			
		} else if (oldDto.getDrkPayMethod() != 2 && dto.getDrkPayMethod() == 2) {
			
			// payInfoNo으로 기존 정보 찾아서 삭제
			deleteResult = this.payMethodMapper.deletePayInfoNo(oldDto);
			updateResult = this.payMethodMapper.updateDrkPay(dto);
			
			return deleteResult + updateResult == 2 ? true : false;
			
		} else if (oldDto.getDrkPayMethod() == 2 && dto.getDrkPayMethod() == 2) {
			
			return true;
		} else {
			
			// 기존 카드/계좌 정보 삭제
			deleteResult = this.payMethodMapper.deletePayInfoNo(oldDto);
			
			// 카드/계좌 유형에 따라 신규 카드/계좌 정보 INSERT
			insertResult = this.payMethodMapper.insertPayInfoNo(dto);
			
			// drk_pay 테이블에 UPDATE
			updateResult = this.payMethodMapper.updateDrkPay(dto);
			
			return deleteResult + insertResult + updateResult == 3 ? true : false;
		}

	}

	
	
}
