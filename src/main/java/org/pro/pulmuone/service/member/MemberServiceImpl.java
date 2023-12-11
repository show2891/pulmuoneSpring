package org.pro.pulmuone.service.member;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean authorizeNICE(String name, String tel, String rrnBirthDate, String rrnGenderCode) throws ClassNotFoundException, SQLException {
		
		Date birthDate = getBirthDate(rrnBirthDate, rrnGenderCode);
		int result = this.memberMapper.selectMemberForNICE(name, tel, birthDate);
		
		
		return result == 1 ? true : false;
	}



	@Override
	public int signUp(MemberDTO dto) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
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
	

}
