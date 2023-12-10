package org.pro.pulmuone.service.event;

import java.util.List;

import org.pro.pulmuone.domain.event.EventListVO;
import org.pro.pulmuone.mapper.event.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<EventListVO> eventList() {
        return eventMapper.eventList();
    }

    @Override
    public List<EventListVO> endedEventList() {
        return eventMapper.endedEventList();
    }
    
    @Override
    public int getTotalRecords() {
        return eventMapper.getTotalRecords();
    }

    @Override
    public int getTotalPages(int numberPerPage) {
        return eventMapper.getTotalPages(numberPerPage);
    }
}