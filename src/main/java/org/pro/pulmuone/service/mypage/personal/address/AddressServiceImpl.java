package org.pro.pulmuone.service.mypage.personal.address;

import java.util.List;

import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.mapper.mypage.personal.address.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<AddrBookDTO> getList(int memberNo) {
		
		List<AddrBookDTO> list = this.addressMapper.selectAddresses(memberNo);
		
		return list;
	}

	@Override
	public AddrBookDTO getAddress(int addrNo) {
		
		AddrBookDTO dto = this.addressMapper.selectAddress(addrNo);
		
		return dto;
	}

	@Override
	public boolean deleteAddress(int addrNo) {

		int deleteResult = this.addressMapper.deleteAddress(addrNo);
		
		return deleteResult == 1 ? true : false;
	}

}
