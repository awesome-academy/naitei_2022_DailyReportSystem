package trainingManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogOutController {
	@PostMapping(name = "/logout")
	public String logOut() {
		
		return "redirect: /TrainingManagementSystem/login";
	}
}
