package org.pro.pulmuone.mapper.mypage.order;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageListDTO;
import org.pro.pulmuone.domain.mypage.order.BoxOrderMypageProductsDTO;

@Mapper
public interface BoxOrderMypageMapper {

	List<BoxOrderMypageDTO> selectBoxOrder(int member_no);

	int getBoxOrderStatus(@Param("member_no") int member_no, @Param("status") int status);

	List<BoxOrderMypageListDTO> selectBoxInfos(@Param("member_no") int member_no, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	List<BoxOrderMypageProductsDTO> selectBoxOrderMypageProducts(int box_order_no);
	
}
