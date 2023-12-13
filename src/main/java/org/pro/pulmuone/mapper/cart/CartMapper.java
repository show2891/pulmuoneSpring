package org.pro.pulmuone.mapper.cart;

import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;

public interface CartMapper {

	// 데일리 조회
		public List<CartVO> daily ( CartVO vo ) ;
		// 데일리 추가
		public int dailyadd(CartVO vo) throws Exception;
	   // 데일리 삭제
	   public int dailydelete(int cart_no);
	   // 데일리 수량 수정
	   public int dailymodify(CartVO vo);
	   
	   // 장바구니 확인용
	   public CartVO check(CartVO vo);
	   
	   // 택배 조회
	   public List<CartVO> box(CartVO vo); 
	   // 택배 추가
	   public int boxadd(CartVO vo) throws Exception;
	   // 박스 삭제
	   public int boxdelete (int cart_no);
	   // 박스 수량 수정
	   public int boxmodify (CartVO vo);
}
