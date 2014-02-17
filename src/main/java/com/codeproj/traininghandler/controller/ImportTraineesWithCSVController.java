package com.codeproj.traininghandler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.service.ImportTraineesWithCSVService;

@Controller
@RequestMapping("/importTrainees.htm")
public class ImportTraineesWithCSVController {

	private ImportTraineesWithCSVService importTraineesWithCSVService;

	@Autowired
	public ImportTraineesWithCSVController(ImportTraineesWithCSVService importTraineesWithCSVService) {
		this.importTraineesWithCSVService = importTraineesWithCSVService;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("importTrainees", "testMessage", importTraineesWithCSVService.getTestMessage());
	}

	public void setImportTraineesWithCSVService(
			ImportTraineesWithCSVService importTraineesWithCSVService) {
		this.importTraineesWithCSVService = importTraineesWithCSVService;
	}
}
