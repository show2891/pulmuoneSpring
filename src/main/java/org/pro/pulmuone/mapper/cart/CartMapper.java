package org.pro.pulmuone.mapper.cart;

import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.curation.CurationVO;

public interface CartMapper {

	// 매일배송 장바구니
	public List<CartVO> dailyadd( CartVO vo ) ;
   // 데일리 장바구니 조회
   public List<CartVO> daily ( CartVO vo ) ;
   public List<CartVO> update ( CartVO vo ) ;
   
   // 삭제
   public int delete (int cart_no);
   
   // 택배배송 장바구니
   public List<CartVO> boxcart(CartVO vo);
   // 택배배송 장바구니 조회
   public List<CartVO> box(CartVO vo);
}
