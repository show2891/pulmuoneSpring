package org.pro.pulmuone.mapper.event;

import java.util.HashMap;
import java.util.List;

import org.pro.pulmuone.domain.event.EventVO;

public interface EventMapper {
	
    void createEvent(EventVO event);

    EventVO viewEvent(int event_no);

    void updateEvent(EventVO event);

    void deleteEvent(int event_no);

    HashMap<String, List<EventVO>> select(int currentPage, int numberPerPage, boolean isOngoing);

    int getTotalRecords();

    int getTotalPages(int numberPerPage, boolean BEvent);
    
}
