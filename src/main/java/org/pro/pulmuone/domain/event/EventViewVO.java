package org.pro.pulmuone.domain.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventViewVO {

	private List<EventImageVO> images;  // ?ù¥ÎØ∏Ï? Î¶¨Ïä§?ä∏
    private EventVO event;
	
}
