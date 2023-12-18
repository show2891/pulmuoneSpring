package org.pro.pulmuone.service.mypage.personal.address;

import java.util.List;

import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;

public interface AddressService {

	List<AddrBookDTO> getList(int memberNo);

	AddrBookDTO getAddress(int addrNo);

	boolean deleteAddress(int addrNo);

}
