package org.pro.pulmuone.mapper.event;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.pro.pulmuone.domain.event.EventWinnerVO;


public interface WinnerMapper {
	
    List<EventWinnerVO> selectList(@Param("start") int start, @Param("end") int end);
    int insert(EventWinnerVO dto);
    int getTotalRecords();
	
    EventWinnerVO getWinner(int win_board_no);
    
}