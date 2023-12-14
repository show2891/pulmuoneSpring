package org.pro.pulmuone.controller.event;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pro.pulmuone.domain.event.EventCommentVO;
import org.pro.pulmuone.domain.event.EventListVO;
import org.pro.pulmuone.domain.event.EventViewVO;
import org.pro.pulmuone.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/event/event/*")
public class EventController {
	
	@Autowired
	private EventService eventService;

	@GetMapping("list")
	public String list(Model model, HttpServletRequest request) {
	    List<EventListVO> events = eventService.eventList();
	    
	    String contextPath = request.getServletContext().getContextPath();
	    for (EventListVO event : events) {
	        if (event.getThumbnail() != null) {
	            String absoluteImgPath = contextPath + event.getThumbnail().getImg_path();
	            event.getThumbnail().setImg_path(absoluteImgPath);
	            System.out.println(absoluteImgPath);
	        }
	    }
	    
	    model.addAttribute("events", events);

	    request.getSession().setAttribute("activeTab", "진행중이벤트");
	    return "event/list.tiles";
	}

	@GetMapping("endList")
	public String endList(Model model, HttpServletRequest request) {
	    List<EventListVO> events = eventService.endedEventList();
	    
	    String contextPath = request.getServletContext().getContextPath();
	    for (EventListVO event : events) {
	        if (event.getThumbnail() != null) {
	            String absoluteImgPath = contextPath + event.getThumbnail().getImg_path();
	            event.getThumbnail().setImg_path(absoluteImgPath);
	        }
	    }
	    
	    model.addAttribute("events", events);

	    request.getSession().setAttribute("activeTab", "종료된이벤트");

	    return "event/endList.tiles";
	}
	
	@GetMapping("view")
	public String view(@RequestParam int event_no, Model model, HttpServletRequest request) {
	    EventViewVO event = eventService.viewEvent(event_no);
	    model.addAttribute("event", event);
	    
	    List<EventCommentVO> comments = eventService.getComments(event_no, 1, 10); // 첫 페이지의 댓글 10개를 가져옴
	    model.addAttribute("comments", comments);
	    
	    if (event_no == 2) {
	        request.getSession().setAttribute("activeTab", "친구초대");
	    } else if (event_no == 25) {
	        request.getSession().setAttribute("activeTab", "회원혜택");
	    } else {
	        request.getSession().setAttribute("activeTab", "진행중이벤트");
	    }
	    
	    return "event/view.tiles";
	}

	/*
	@GetMapping("winner")
	public String winner(Model model, HttpServletRequest request) {
		// 당첨자 정보를 가져오는 코드를 여기에 작성하십시오.

		request.getSession().setAttribute("activeTab", "당첨자발표");

		return "winnerList";
	}

	
	*/
}
