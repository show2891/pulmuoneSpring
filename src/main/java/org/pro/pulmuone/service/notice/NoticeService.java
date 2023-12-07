package org.pro.pulmuone.service.notice;

import java.sql.SQLException;
import java.util.ArrayList;

import org.pro.pulmuone.domain.notice.NoticeDTO;

public interface NoticeService {
	ArrayList<NoticeDTO> getNoticeList(int pageNo, int numberPerPage) throws SQLException;
	
	int getTotalRows() throws SQLException;
	
	int getTotalPages(int numberPerPage) throws SQLException;
}
