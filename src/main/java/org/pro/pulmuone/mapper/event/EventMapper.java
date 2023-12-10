package org.pro.pulmuone.mapper.event;

import java.util.List;

import org.pro.pulmuone.domain.event.EventListVO;

public interface EventMapper {
	
    List<EventListVO> eventList();
    List<EventListVO> endedEventList();
    int getTotalRecords();
    int getTotalPages(int numberPerPage);
    
}

