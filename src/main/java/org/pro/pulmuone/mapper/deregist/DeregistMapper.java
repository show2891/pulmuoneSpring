package org.pro.pulmuone.mapper.deregist;

import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.deregist.DeregistDTO;

public interface DeregistMapper {

	public int insertDeregist(@Param("dto") DeregistDTO dto);
	
}
