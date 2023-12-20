package org.pro.pulmuone.mapper.curation;

import java.util.List;

import org.pro.pulmuone.domain.curation.CurationVO;
import org.pro.pulmuone.domain.curation.KidsVO;

public interface CurationMapper {

   // 1. 큐레이션 결과
   public List<CurationVO> CurationSel(CurationVO vo);

   // 2. 키즈 상품
   public List<KidsVO> kids(KidsVO vo);
   
   // 모달창
   public List<KidsVO> modal( KidsVO vo );

}
