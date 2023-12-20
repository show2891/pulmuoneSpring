package org.pro.pulmuone.service.event;

import java.util.List;

import org.pro.pulmuone.domain.event.EventWinnerVO;
import org.pro.pulmuone.mapper.event.WinnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerServiceImpl implements WinnerService {

    @Autowired
    private WinnerMapper winnerMapper;

    @Override
    public List<EventWinnerVO> selectList(int start, int end) {
        return winnerMapper.selectList(start, end);
    }

    @Override
    public void insert(EventWinnerVO vo) {
        winnerMapper.insert(vo);
    }

    @Override
    public int getTotalRecords() {
        return winnerMapper.getTotalRecords();
    }
    
    @Override
    public EventWinnerVO getWinner(int win_board_no) {
        return winnerMapper.getWinner(win_board_no);
    }


}