package kr.youhost.ems.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import kr.youhost.ems.api.response.Response;

@Controller
public class ApiBaseController {
	Logger logger = LoggerFactory.getLogger(ApiBaseController.class);
	
	protected Response okResponse() {
		return Response.ok();
	}
	
}
