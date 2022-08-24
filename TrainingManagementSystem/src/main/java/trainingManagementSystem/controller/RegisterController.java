package trainingManagementSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import trainingManagementSystem.model.User;
import trainingManagementSystem.service.AuthenticationServices;

@Controller
public class RegisterController {

	@Autowired
	AuthenticationServices authenticationServices;

	@GetMapping("register")
	public String index(ModelMap model) {
		model.addAttribute("user", new User());
		return "authentication/register";
	}

	@PostMapping("register")
	public String saveUser(ModelMap model, @Valid User user, final BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "authentication/register";
		}
		authenticationServices.registerUser(user);
		return "redirect: /TrainingManagementSystem/";
	}
}
