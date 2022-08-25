package trainingManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = { "/admin/DivisionInfomation" }, method = RequestMethod.GET)
	public String DivisionInfomation(@RequestParam int id, ModelMap model) {
		model.addAttribute("getUserByDivisionId", userServices.getAllUserByDivisionId(id));
		model.addAttribute("getDivisionById", divisionService.getById(id));
		return "admins/getUsersOfDivision";
	}

	// Add a Division

	@RequestMapping(value = { "/admin/divisions/create" }, method = RequestMethod.POST)
	public String saveDivision(ModelMap model, @ModelAttribute("division") Division division) {
		division.setManager(userServices.getById(division.getManager().getId(), null));
		divisionService.saveDivision(division);
		model.addAttribute("message", "Success adding division!");
		return "redirect:/admin";
	}

	// Remove a Division
	@RequestMapping(value = { "/admin/divisions/remove" })
	public String RemoveDivision(@RequestParam int id, @ModelAttribute("division") Division division) {
		divisionService.getById(id);
		divisionService.deleteDivision(id); 
		return "redirect:/admin";

	}

}
