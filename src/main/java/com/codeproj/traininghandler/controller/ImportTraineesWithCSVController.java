package com.codeproj.traininghandler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.manager.ImportTraineesWithCSVManager;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;

@Controller
@RequestMapping("/importTrainees.htm")
public class ImportTraineesWithCSVController {
	private static final Logger logger = Logger.getLogger(ImportTraineesWithCSVController.class);
	
	private ImportTraineesWithCSVManager importTraineesWithCSVManager;
	
	@Autowired
	private TrainingTypeManager trainingTypeManager;
	
	
    @Autowired
    private MessageSource messageSource;
    
	@Autowired
	public ImportTraineesWithCSVController(ImportTraineesWithCSVManager importTraineesWithCSVManager) {
		this.importTraineesWithCSVManager = importTraineesWithCSVManager;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
//		Locale currentLocale = LocaleContextHolder.getLocale();
//		String message = messageSource.getMessage("welcome.springmvc", null, currentLocale);
//		messageSource.getMessage(arg0, arg1)
//		logger.debug(message);
		return new ModelAndView("importTrainees", "testMessage", importTraineesWithCSVManager.getTestMessage());
	}

	public void setimportTraineesWithCSVManager(
			ImportTraineesWithCSVManager importTraineesWithCSVManager) {
		this.importTraineesWithCSVManager = importTraineesWithCSVManager;
	}


	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setTrainingTypeManager(TrainingTypeManager trainingTypeManager) {
		this.trainingTypeManager = trainingTypeManager;
	}
}
