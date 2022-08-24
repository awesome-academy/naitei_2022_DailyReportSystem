package trainingManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trainingManagementSystem.model.Division;
import trainingManagementSystem.model.User;
import trainingManagementSystem.service.DivisionServices;
import trainingManagementSystem.service.UserServices;

@Controller
public class AdminController {
	@Autowired
	DivisionServices divisionService;
	@Autowired
	UserServices userServices;

	// List all Divisions
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String adminIndex(ModelMap model) {
		model.addAttribute("listDivisions", this.divisionService.getAllDivision());
		return "admins/index";
	}


	@RequestMapping(value = { "/admin/divisions/new" }, method = RequestMethod.GET)
	public String AddNewDivision(ModelMap model) {
		model.addAttribute("loadUsersNotinManagerID", userServices.loadUsersNotinManagerID());
		model.addAttribute("division", new Division());
		return "admins/createDivision";
	}

	//Add a Division
	@RequestMapping(value = { "/admin/divisions/create" }, method = RequestMethod.POST)
	public String saveDivision(ModelMap model, @ModelAttribute("division") Division division) {
		divisionService.saveDivision(division);
		
		model.addAttribute("message", "Success adding division!");
		return "redirect:/admin";
	}

}
