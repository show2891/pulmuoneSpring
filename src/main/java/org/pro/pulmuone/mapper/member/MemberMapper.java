package org.pro.pulmuone.mapper.member;

import java.sql.Date;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.member.MemberDTO;

public interface MemberMapper {
	public int selectMemberForDuplicateIdCheck(String memberId) throws ClassNotFoundException, SQLException;
	
	public int selectMemberForNICE(String name, String tel, Date birthDate) throws ClassNotFoundException, SQLException;

	public MemberDTO selectMemberWithInvCode(String invCode) throws ClassNotFoundException, SQLException;
	
	public int insert(MemberDTO dto) throws ClassNotFoundException, SQLException;

	public int insertAuthForSignUp(String memberId) throws ClassNotFoundException, SQLException;
	
	
	//회원정보 + 권한정보 얻어오는 메서드
	public MemberDTO read(@Param("userid") String userid);
}
