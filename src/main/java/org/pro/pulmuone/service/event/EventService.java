package org.pro.pulmuone.service.event;

import java.util.ArrayList;
import java.util.HashMap;

import org.pro.pulmuone.domain.event.EventVO;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
	
    HashMap<String, ArrayList<EventVO>> getEvents(int currentPage, int numberPerPage) throws Exception;

    int getTotalPages(int numberPerPage) throws Exception;
    
}