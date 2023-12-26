package org.pro.pulmuone.mapper.mypage.drink.payMethod;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.drink.payMethod.PayMethodDTO;

public interface PayMethodMapper {

	List<PayMethodDTO> selectPayMethodList(@Param("memberNo") int memberNo);

	PayMethodDTO selectPayMethod(@Param("drkOrderNo") long drkOrderNo);

	int deletePayInfoNo(@Param("dto") PayMethodDTO dto);

	int insertPayInfoNo(@Param("dto") PayMethodDTO dto);

	int updateDrkPay(@Param("dto") PayMethodDTO dto);


}
