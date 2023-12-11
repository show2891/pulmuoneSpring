package org.pro.pulmuone.mapper.taste;

import java.util.List;

import org.pro.pulmuone.domain.product.ProductsDTO;
import org.pro.pulmuone.domain.taste.TasteDTO;

public interface TasteMapper {
//	상품 리스트
	List<TasteDTO> search(TasteDTO dto);

	
}
