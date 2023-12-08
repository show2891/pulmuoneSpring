package org.pro.pulmuone.service.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pro.pulmuone.domain.event.EventVO;
import org.pro.pulmuone.mapper.event.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EventServiceImpl implements EventService {
	
    @Autowired
    private EventMapper eventMapper;

    @Override
    public HashMap<String, ArrayList<EventVO>> getEvents(int currentPage, int numberPerPage) throws Exception {
        int begin = (currentPage - 1) * numberPerPage + 1;
        int end = begin + numberPerPage - 1;

        List<EventVO> eventList = eventMapper.select(begin, end, true);

        ArrayList<EventVO> onEvent = new ArrayList<>();
        ArrayList<EventVO> endEvent = new ArrayList<>();

        for (EventVO event : eventList) {
            if (!event.getEvent_end().toLocalDate().isBefore(LocalDate.now())) {
                onEvent.add(event);
            } else {
                endEvent.add(event);
            }
        }

        HashMap<String, ArrayList<EventVO>> eventMap = new HashMap<>();
        eventMap.put("onEvent", onEvent);
        eventMap.put("endEvent", endEvent);

        return eventMap;
    }

    @Override
    public int getTotalPages(int numberPerPage) throws Exception {
        int totalRecords = eventMapper.getTotalRecords();
        int totalPages = (int) Math.ceil((double) totalRecords / numberPerPage);
        return totalPages;
    }
    
}