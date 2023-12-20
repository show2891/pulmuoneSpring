package org.pro.pulmuone.security.domain;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CurrentUser extends User {

	private MemberDTO member;
	
//	private String password;
//	private String username;
//	private Set<GrantedAuthority> authorities;
//	private boolean enabled;

	public CurrentUser(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		this(username, password, enabled, true, true, true, authorities);
	}
	
	public CurrentUser(MemberDTO vo) {
		this(vo.getMemberId(), vo.getPwd(), (vo.getEnabled() == 1 ? true : false), vo.getAuthList().stream().map( auth -> new SimpleGrantedAuthority( auth.getAuthority() ) ).collect( Collectors.toList() ));
		 // List<AuthVO>  -> Collection<? extends GrantedAuthority> authorities
	
		this.member = vo;
		
	}
	
	// MemberVO 객체를 UserDetails에 삽입하기 위함

	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	
//	public CurrentUser(MemberDTO vo) {
//		super(vo.getMemberId(), vo.getPwd(), vo.getAuthList().stream().map( auth -> new SimpleGrantedAuthority( auth.getAuthority() ) ).collect( Collectors.toList() ));
//		 // List<AuthVO>  -> Collection<? extends GrantedAuthority> authorities
//	
//		this.member = vo;
//		
//	}
	
	
//	// 비활성화된 회원은 로그인 불가 처리
//	@Override
//	public boolean isEnabled() {
//		return this.member.getEnabled() == 1 ? true : false;
//	}
	
	
	

}
