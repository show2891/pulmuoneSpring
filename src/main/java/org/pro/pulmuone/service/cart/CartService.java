package org.pro.pulmuone.service.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.faq.FaqDTO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

	// 데일리 조회
	public List<CartVO> daily ( CartVO vo ) ;
	// 데일리 추가
	public int dailyadd(CartVO vo) throws Exception;
	// 데일리 삭제
	public int dailydelete(int cart_no);
	// 데일리 수량 수정
	public int dailymodify(CartVO vo);

	// 택배 조회
	public List<CartVO> box(CartVO vo); 
	// 택배 추가
	public int boxadd(CartVO vo) throws Exception;
	// 박스 삭제
	public int boxdelete (int cart_no);
	// 박스 수량 수정
	public int boxmodify (CartVO vo);
}
