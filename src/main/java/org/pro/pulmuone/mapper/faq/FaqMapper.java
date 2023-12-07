package org.pro.pulmuone.mapper.faq;

import java.sql.SQLException;
import java.util.ArrayList;

import org.pro.pulmuone.domain.faq.FaqDTO;

public interface FaqMapper {
	// 1. 목록 조회
	//ArrayList<FaqDTO> select(Connection conn, int category, int being, int end, int total) throws SQLException;
	ArrayList<FaqDTO> select(int pageNo, int category,  int numberPerPage) throws SQLException;
}
