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

	@RequestMapping(value = "divisions/{id}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable("id") int id) {
		
		ModelAndView mv = new ModelAndView("managers/manage_division");
		mv.addObject("users", userServices.getUserByDivisionId(id));	 
		mv.addObject("division", divisionServices.getById(id));
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
	
	@RequestMapping(value="/{id}/remove", method=RequestMethod.POST)
	@ResponseBody
	public String removeUserFromDivision(@PathVariable("id") int id) {
			userServices.removeUserFromDivision(id);
			return "success";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("managers/add_user");
		mv.addObject("users", userServices.getNewUsers());
		return mv;
	}
	
	@RequestMapping(value="/add-user/{divisionId}/{userId}", method=RequestMethod.POST)
	@ResponseBody
	public String addUserToDivision(@PathVariable("userId") int userId, @PathVariable("divisionId") int divisionId) {
		try {
			userServices.addUserToDivision(divisionId, userId);
			return "success";
		} catch(Exception e) {
			return "error";
		}
	}
}
