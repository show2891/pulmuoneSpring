package org.pro.pulmuone.mapper.mypage.personal.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;

public interface AddressMapper {

	List<AddrBookDTO> selectAddressList(@Param("memberNo") int memberNo);

	AddrBookDTO selectAddress(@Param("addrNo") int addrNo);

	int deleteAddress(@Param("addrNo") int addrNo);

	int insertAddress(@Param("dto") AddrBookDTO dto);

	int updateAddress(@Param("dto") AddrBookDTO dto);

	int updateDefaultAddrForReset(@Param("memberNo") int memberNo);

	int updateDefaultAddr(@Param("addrNo") int addrNo);

}
