package org.pro.pulmuone.service.coupon;

import java.util.List;

import org.pro.pulmuone.domain.order.HaveCouponDTO;

public interface CouponService {
	
	List<HaveCouponDTO> getCoupons(int memberNo);
	
	int getMemberInfo(String memberId);
	
	String getUserName(int memberNo);
}