package com.codeproj.traininghandler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GatherTraineeHistoryAndDetailsController {
	private static final Logger logger = Logger.getLogger(GatherTraineeHistoryAndDetailsController.class);
	
	@RequestMapping("/gatherTraineeInfo")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, ModelMap model) throws Exception {
		model.addAttribute("isPublicPage", new Boolean(true));
		
		logger.debug("Going to gatherTraineeInfo page..");
		return new ModelAndView("gatherTraineeInfo");
	}

	@RequestMapping("/gatherTraineeInfo/success")
	protected ModelAndView handleSuccessRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, ModelMap model) throws Exception {
		model.addAttribute("isPublicPage", new Boolean(true));
		
		logger.debug("Going to gatherTraineeInfo success page..");
		return new ModelAndView("gatherTraineeInfoSuccess");
	}
}
