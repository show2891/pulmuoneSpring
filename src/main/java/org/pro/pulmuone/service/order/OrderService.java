package org.pro.pulmuone.service.order;

import java.util.List;

import org.pro.pulmuone.domain.order.FranchiseDTO;
import org.pro.pulmuone.domain.order.OrderAddrBookDTO;
import org.pro.pulmuone.domain.order.daily.DailyItemInfoDTO;

public interface OrderService {
	
	OrderAddrBookDTO getMemberInfo(String username);
	
	List<OrderAddrBookDTO> getAddrInfo(int member_no, int pageNo);
	
	List<DailyItemInfoDTO> getproductList(String member_no, int pageNo);
	
	FranchiseDTO getFranchise(double latitude, double longitude, int fc_type);

}
