package trainingManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import trainingManagementSystem.model.User;
import trainingManagementSystem.service.AuthenticationServices;

@Controller
public class LoginController {
	@Autowired
	AuthenticationServices authenticationServices;

	@GetMapping("login")
	public String index(ModelMap model) {
		model.addAttribute("user", new User());
		return "authentication/login";
	}
}
