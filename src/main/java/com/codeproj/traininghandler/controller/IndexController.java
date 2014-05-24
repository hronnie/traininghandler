package com.codeproj.traininghandler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
//		Locale currentLocale = LocaleContextHolder.getLocale();
//		String message = messageSource.getMessage("welcome.springmvc", null, currentLocale);
//		messageSource.getMessage(arg0, arg1)
//		logger.debug(message);
		return new ModelAndView("index");
	}

}
