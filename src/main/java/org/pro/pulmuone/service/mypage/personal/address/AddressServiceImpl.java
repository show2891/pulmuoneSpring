package org.pro.pulmuone.service.mypage.personal.address;

import java.util.List;

import org.pro.pulmuone.domain.mypage.personal.address.AddrBookDTO;
import org.pro.pulmuone.mapper.mypage.personal.address.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<AddrBookDTO> getList(int memberNo) {
		
		List<AddrBookDTO> list = this.addressMapper.selectAddressList(memberNo);
		
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

	@Override
	public boolean writeAddress(AddrBookDTO dto) {
		
		int insertResult = this.addressMapper.insertAddress(dto);
		
		return insertResult == 1 ? true : false;
	}

	@Override
	public boolean modifyAddress(AddrBookDTO dto) {

		int updateResult = this.addressMapper.updateAddress(dto);
		
		return updateResult == 1 ? true : false;
	}

	@Override
	@Transactional
	public boolean setDefaultAddr(int memberNo, int addrNo) {
		
		int resetDefaultAddrResult = this.addressMapper.updateDefaultAddrForReset(memberNo);
		int updateResult = this.addressMapper.updateDefaultAddr(addrNo);

		return updateResult == 1 ? true : false;
	}

}
