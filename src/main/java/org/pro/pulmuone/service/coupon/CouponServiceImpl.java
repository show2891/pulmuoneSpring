package org.pro.pulmuone.service.coupon;

import java.util.List;

import org.pro.pulmuone.domain.order.HaveCouponDTO;
import org.pro.pulmuone.mapper.coupon.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
	
    @Autowired
    private CouponMapper couponMapper;


    @Override
    public List<HaveCouponDTO> getCoupons(int memberNo) {
        return couponMapper.findCouponsByUserNo(memberNo);
    }
    
    @Override
    public int getMemberInfo(String memberId) {
        return couponMapper.getMemberInfo(memberId);
    }
    
    @Override
    public String getUserName(int memberNo) {
        return couponMapper.getUserName(memberNo);
    }
    
}