package org.pro.pulmuone.service.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.pro.pulmuone.domain.cart.CartVO;
import org.pro.pulmuone.domain.faq.FaqDTO;
import org.pro.pulmuone.mapper.cart.CartMapper;
import org.pro.pulmuone.mapper.faq.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	public CartMapper cartMapper;


	@Override
	public List<CartVO> daily(CartVO vo) {
		return this.daily(vo);
	}

	@Override
	public int dailyadd(CartVO vo) throws Exception {
		CartVO ck = cartMapper.check(vo);
		
		if (ck != null) {
			return 2;
		}
		return this.dailyadd(vo);
	}

	@Override
	public int dailydelete(int cart_no) {
		return this.cartMapper.dailydelete(cart_no);
	}

	@Override
	public int dailymodify(CartVO vo) {
		return this.cartMapper.dailymodify(vo);
	}

	@Override
	public List<CartVO> box(CartVO vo) {
		return this.cartMapper.box(vo);
	}

	@Override
	public int boxadd(CartVO vo) throws Exception {
	CartVO ck = cartMapper.check(vo);
		
		if (ck != null) {
			return 2;
		}
		return this.boxadd(vo);
	}

	@Override
	public int boxdelete(int cart_no) {
		return this.cartMapper.boxdelete(cart_no);
	}

	@Override
	public int boxmodify(CartVO vo) {
		return this.cartMapper.boxmodify(vo);
	}


	
}
