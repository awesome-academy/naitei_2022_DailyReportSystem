package trainingManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value = { "/", "divisions/{id}" }, method = RequestMethod.GET)
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("managers/manages_division");
		List<User> users = userServices.getAllUser();
		mv.addObject("users", users);	
		
		Division division = divisionServices.getById(1);
		mv.addObject("division", division);
		
		return mv;
	}
}
