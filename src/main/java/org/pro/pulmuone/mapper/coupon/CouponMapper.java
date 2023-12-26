package org.pro.pulmuone.mapper.coupon;

import java.util.List;

import org.pro.pulmuone.domain.order.HaveCouponDTO;

public interface CouponMapper {
	
	List<HaveCouponDTO> findCouponsByUserNo(int memberNo);
	
	int getMemberInfo(String memberId);
    
	String getUserName(int memberNo);
	
}

