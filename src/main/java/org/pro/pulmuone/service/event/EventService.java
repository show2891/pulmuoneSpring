package org.pro.pulmuone.service.event;

import java.util.List;

import org.pro.pulmuone.domain.event.EventListVO;
import org.springframework.stereotype.Service;

public interface EventService {
	
    List<EventListVO> eventList();
    List<EventListVO> endedEventList();
    int getTotalRecords();
    int getTotalPages(int numberPerPage);
    
}