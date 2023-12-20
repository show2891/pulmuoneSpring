package org.pro.pulmuone.service.mypage.personal.info;

import org.pro.pulmuone.domain.member.MemberDTO;

public interface PInfoService {

	public MemberDTO getMemberInfo(String memberId);

	public boolean modifyMemberInfo(String memberId, MemberDTO dto);

	public boolean checkPwd(String storedPwd, String pwd);

}
