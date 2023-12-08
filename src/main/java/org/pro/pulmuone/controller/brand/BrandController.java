package org.pro.pulmuone.controller.brand;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info/*")
public class BrandController {
	
	@RequestMapping("brand")
	public String brand(Model model) throws Exception {						
		return "brand/brand.tiles";
	}
	
	@RequestMapping("greenStory/rule")
	public String rule(Model model) throws Exception {						
		return "brand/rule.tiles";
	}
	
	@RequestMapping("greenStory/production")
	public String production(Model model) throws Exception {						
		return "brand/production.tiles";
	}
	
	@RequestMapping("greenStory/customer")
	public String customer(Model model) throws Exception {						
		return "brand/customer.tiles";
	}
	
	@RequestMapping("greenStory/slogan")
	public String slogan(Model model) throws Exception {						
		return "brand/slogan.tiles";
	}
	
	@RequestMapping("greenStory/environment")
	public String environment(Model model) throws Exception {						
		return "brand/environment.tiles";
	}

	@RequestMapping("greenStory/{번호}")
	public String brandimg(Model model) throws Exception {						
		return "brand/brandimg.tiles";
	}
}
