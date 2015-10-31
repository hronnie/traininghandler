package com.codeproj.traininghandler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manageTraining")
public class ManageTrainingsController {

	private static final Logger logger = Logger.getLogger(ManageTrainingsController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		ModelAndView mav = new ModelAndView("manageTraining");
		mav.addObject("isNotMainPage", new Boolean(true));
		mav.addObject("isPublicPage", new Boolean(false));
		logger.debug("Going to manageTraining page..");
		return mav;
	}

}
