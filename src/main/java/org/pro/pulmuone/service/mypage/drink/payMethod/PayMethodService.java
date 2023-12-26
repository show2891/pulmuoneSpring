package org.pro.pulmuone.service.mypage.drink.payMethod;

import java.util.List;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.domain.mypage.BankDTO;
import org.pro.pulmuone.domain.mypage.drink.payMethod.PayMethodDTO;

public interface PayMethodService {

	List<PayMethodDTO> getList(int memberNo);

	PayMethodDTO getPaymethod(long drkOrderNo);

	List<BankDTO> getBankList();

	boolean modifyPayMethod(PayMethodDTO dto);

	MemberDTO getMemberTel(String memberId);

}
