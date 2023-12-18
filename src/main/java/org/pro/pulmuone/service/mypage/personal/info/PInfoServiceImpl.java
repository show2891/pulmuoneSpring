package org.pro.pulmuone.service.mypage.personal.info;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PInfoServiceImpl implements PInfoService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MemberDTO getMemberInfo(String memberId) {
		
		MemberDTO dto = this.memberMapper.selectMemberForInfo(memberId);
		
		return dto;
	}
	
	@Override
	public boolean modifyMemberInfo(String memberId, MemberDTO dto) {

		int updateResult = 0;
		if (dto.getNewPassword() != null) {
			String encodedNewPwd = this.passwordEncoder.encode(dto.getNewPassword());			
			dto.setNewPassword(encodedNewPwd);
		}
		
		updateResult = this.memberMapper.updateInfo(memberId, dto);

		return updateResult == 1 ? true : false;
	}

	@Override
	public boolean checkPwd(String storedPwd, String pwd) {
		
		boolean pwdIsMatch = this.passwordEncoder.matches(pwd, storedPwd);
		
		return pwdIsMatch;
	}

	
	
}
