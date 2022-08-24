package trainingManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import trainingManagementSystem.model.*;
import trainingManagementSystem.service.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	UserServices userServices;
	@Autowired
	DivisionServices divisionServices;
	
	@Autowired
	ManagerServices managerService;

	@RequestMapping(value = { "/", "divisions/{id}" }, method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("managers/manage_division");
		List<User> users = userServices.getAllUser();
		mv.addObject("users", users);

		Division division = divisionServices.getById(1);
		mv.addObject("division", division);

		return mv;
	}

	@RequestMapping(value = { "/reports" }, method = RequestMethod.GET)
	public String getAllReport(ModelMap model, @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
		int pageSize = 6;

		// TODO: get length reports -> handle pagination jsp
		List<Report> reports = divisionServices.paginationReports(1, pageNumber - 1, pageSize);
		model.addAttribute("reports", reports);

		return "managers/reports";
	}

	@RequestMapping(value = { "/reports/{id}" }, method = RequestMethod.GET)
	public String getById(ModelMap model, @PathVariable Integer id) {
		// not lock
		Report report = managerService.getReportById(id, false);
		model.addAttribute("report", report);

		return "managers/comments";
	}
}
