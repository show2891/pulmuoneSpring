package org.pro.pulmuone.service.member;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean authorizeNICE(String name, String tel, String rrnBirthDate, String rrnGenderCode) throws ClassNotFoundException, SQLException {
		
		Date birthDate = getBirthDate(rrnBirthDate, rrnGenderCode);
		int result = this.memberMapper.selectMemberForNICE(name, tel, birthDate);
		
		
		return result == 1 ? true : false;
	}

	@Override
	public boolean duplicateIdCheck(String memberId) throws ClassNotFoundException, SQLException {
	
		int  result = this.memberMapper.selectMemberForDuplicateIdCheck(memberId);
		
		return result == 1 ? true : false;
	}
	
	@Override
	public MemberDTO invCodeCheck(String invCode) throws ClassNotFoundException, SQLException {
		
		MemberDTO dto = null;
		dto = this.memberMapper.selectMemberWithInvCode(invCode);
		
		return dto; 
	}

	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int signUp(MemberDTO dto) throws ClassNotFoundException, SQLException {
		
		String invCode = getRandomInvCode();
		String pwd = dto.getPwd();
		
		dto.setPwd(this.passwordEncoder.encode(pwd));
		dto.setInvCode(invCode);
		
		int insertResult = this.memberMapper.insert(dto); // 회원정보 등록
		int insertAuthResult = this.memberMapper.insertAuthForSignUp(dto.getMemberId()); // 회원 권한 부여
		
		return insertResult + insertAuthResult;
	}
	
	
	
	@Override
	public Date getBirthDate(String rrnBirthDate, String rrnGenderCode) {
		Date birthDate = null;

		System.out.println(rrnBirthDate + "-"+ rrnGenderCode);
//		String[] birthIn1800s = {"9","0"};
		String[] birthIn1900s = {"1","2","5","6"};
		String[] birthIn2000s = {"3","4","7","8"};
		
		String birthYear;
		
		if (Arrays.asList(birthIn1900s).contains(rrnGenderCode)) {
			birthYear = "19" + rrnBirthDate.substring(0, 2); 
		} else if (Arrays.asList(birthIn2000s).contains(rrnGenderCode)) {
			birthYear = "20" + rrnBirthDate.substring(0, 2);
		} else {
			birthYear = "18" + rrnBirthDate.substring(0, 2);			
		}		
		
		String birthMonth = rrnBirthDate.substring(2, 4); 
		String birthDay = rrnBirthDate.substring(4, 6);

		birthDate = Date.valueOf(String.format("%s-%s-%s", birthYear, birthMonth, birthDay));
		
		return birthDate;
	}
	
	
	private String getRandomInvCode() {
		String charRange = "abcdefgehijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int charRangeLength = charRange.length();
		int invCodeLength = 5;
		int randomIndex;
		
		StringBuilder invCodeBuilder = new StringBuilder(invCodeLength);
		for (int i = 0; i < invCodeLength; i++) {
			randomIndex = (int) ( Math.random() * (charRangeLength) );
			invCodeBuilder.append(charRange.charAt(randomIndex));
		}
		
		String invCode = invCodeBuilder.toString();
		
		return invCode;
	}	
	
}
