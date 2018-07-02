package org.shop.backend.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.shop.backend.common.dto.AdministratorDto;
import org.shop.backend.web.controller.BaseController;
import org.shop.backend.web.security.StrengthenUsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员控制器
 * @author VIC
 *
 */
@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController {
	
	@RequestMapping("/add")
	public String add(AdministratorDto administratorDto){
		
		administratorService.add(administratorDto);
		return "message";
	}
	
	@RequestMapping("/list")
	public String list(AdministratorDto administratorDto,HttpServletRequest request){
		List<AdministratorDto> admins =administratorService.findByNames(administratorDto);
		request.setAttribute("admins", admins);
		return "administrator_list";
	}
	
	@RequestMapping("/login")
	public String login(AdministratorDto administratorDto){
		StrengthenUsernamePasswordToken token = new StrengthenUsernamePasswordToken(administratorService, administratorDto);
		Subject currentUser = SecurityUtils.getSubject(); 
		currentUser.login(token);
		return "redirect:/shops_index";
	}

}
