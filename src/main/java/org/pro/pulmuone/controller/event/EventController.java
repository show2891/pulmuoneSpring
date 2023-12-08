package org.pro.pulmuone.controller.event;

import java.util.ArrayList;
import java.util.HashMap;

import org.pro.pulmuone.domain.PageDTO;
import org.pro.pulmuone.domain.event.EventVO;
import org.pro.pulmuone.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/event/*")
public class EventController {
	
	@Autowired
    private EventService eventService;

    @GetMapping("list")
    public String getEventList(@RequestParam(value = "currentpage", defaultValue = "1") int currentPage, Model model) {
        int numberPerPage = 10; // 한 페이지에 출력할 게시글 수
        int numberOfPageBlock = 10;
        int totalPages = 0; // 총 페이지 수

        HashMap<String, ArrayList<EventVO>> eventMap = null;
        try {
            eventMap = eventService.getEvents(currentPage, numberPerPage);
            totalPages = eventService.getTotalPages(numberPerPage);
            PageDTO pDto = new PageDTO(currentPage, numberPerPage, numberOfPageBlock, totalPages);
            
            // 진행 중인 이벤트 리스트를 가져옵니다.
            ArrayList<EventVO> onEvent = eventMap.get("onEvent");

            // 1. 포워딩 전 데이터 저장
            model.addAttribute("onEvent", onEvent);
            model.addAttribute("pDto", pDto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "event/list";
    }
	
}

