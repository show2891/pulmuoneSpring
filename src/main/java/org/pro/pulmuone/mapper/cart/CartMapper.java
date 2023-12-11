package org.pro.pulmuone.mapper.cart;

import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.curation.CurationVO;

public interface CartMapper {

	// 매일배송 장바구니
   public List<CurationVO> dailycart ( CartVO vo ) ;
   
   // 택배배송 장바구니
   public List<CurationVO> boxcart (CartVO vo);
}
