package org.pro.pulmuone.mapper.cart;

import java.sql.SQLException;
import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.curation.CurationVO;

public interface CartMapper {

   // 장바구니
   // int cartadd(Connection con, int tag) throws SQLException;
   
	// 매일배송 장바구니
   public List<CurationVO> dailycart ( CartVO vo )  throws ClassNotFoundException, SQLException;
   
   // 택배배송 장바구니
   public List<CurationVO> boxcart (CartVO vo) throws ClassNotFoundException, SQLException;
   //ArrayList<CurationDTO> addcart(Connection con, ArrayList<String> products_tag) throws SQLException;
}
