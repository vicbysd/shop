package org.shop.backend.web.controller;

import org.shop.backend.service.system.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	protected IAdministratorService administratorService;
	private String message;

}
