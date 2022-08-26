package trainingManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import trainingManagementSystem.model.User;
@Controller
public class HomeController {
	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("user", new User());
		return "users/index";
	}

}
