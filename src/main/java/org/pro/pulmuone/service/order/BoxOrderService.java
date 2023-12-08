package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.member.MemberDTO;
import org.pro.pulmuone.domain.order.box.BoxItemInfoDTO;
import org.pro.pulmuone.domain.order.box.BoxOrderItemDTO;

public interface BoxOrderService {
	
	List<BoxItemInfoDTO> selectItems(List<BoxOrderItemDTO> items);
	
	MemberDTO getMemberInfo(String username);

}
