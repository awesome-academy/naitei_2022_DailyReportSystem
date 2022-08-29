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
import trainingManagementSystem.system.exception.UserAlreadyExistException;

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
			model.addAttribute("message", "Đăng ký thất bại");
			return "authentication/register";
		}
		
		try {
			authenticationServices.registerUser(user);
		}
		catch (UserAlreadyExistException e) {
			model.addAttribute("message", "Đăng ký thất bại");
			bindingResult.rejectValue("email", "users.email", "Một tài khoản khác đã sử dụng email này.");
            return "authentication/register";
		}
		
		model.addAttribute("message", "Đăng ký thành công");
		return "redirect: /TrainingManagementSystem/login";
	}
}
