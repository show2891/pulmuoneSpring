package org.pro.pulmuone.controller.taste;

import java.sql.SQLException;
import java.util.List;

import org.pro.pulmuone.domain.product.ProductsDTO;
import org.pro.pulmuone.domain.taste.TasteDTO;
import org.pro.pulmuone.mapper.taste.TasteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/taste/*")
@Log4j
public class TasteController {
	
	@Autowired
	private TasteMapper mapper;
	
	@RequestMapping("taste")
	public String taste(TasteDTO dto, Model model
			, @RequestParam(value = "pkgIdx",required = false  ) String idx
						) throws ClassNotFoundException, SQLException {
		log.info("taste" );
				
		dto.setGift_no(Integer.parseInt(idx));		
		List<TasteDTO> searchlist = this.mapper.search(dto);
		
		model.addAttribute("searchlist",searchlist);
		
		return "taste/taste.tiles";
	}


}
