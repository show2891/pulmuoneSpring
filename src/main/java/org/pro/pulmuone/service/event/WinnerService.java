package org.pro.pulmuone.service.event;

import java.util.List;

import org.pro.pulmuone.domain.event.EventWinnerVO;

public interface WinnerService {
	
    List<EventWinnerVO> selectList(int start, int end);
    void insert(EventWinnerVO vo);
    int getTotalRecords();
    
    EventWinnerVO getWinner(int win_board_no);
}