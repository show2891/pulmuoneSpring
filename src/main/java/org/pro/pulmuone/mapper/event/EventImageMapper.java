package org.pro.pulmuone.mapper.event;

import java.util.List;

import org.pro.pulmuone.domain.event.EventImageVO;

public interface EventImageMapper {

    List<EventImageVO> getEventImages(int eventNo);
	
}
