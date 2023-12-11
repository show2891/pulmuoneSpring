package org.pro.pulmuone.service.member;

import java.sql.Date;
import java.sql.SQLException;

import org.pro.pulmuone.domain.member.MemberDTO;

public interface MemberService {

	boolean authorizeNICE(String name, String tel, String rrnBirthDate, String rrnGenderCode) throws ClassNotFoundException, SQLException;

	int signUp(MemberDTO dto) throws ClassNotFoundException, SQLException;

	Date getBirthDate(String rrnBirthDate, String rrnGenderCode);

}
