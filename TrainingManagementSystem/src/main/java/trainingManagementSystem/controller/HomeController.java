package trainingManagementSystem.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HomeController {
	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public String index(HttpServletResponse httpServletResponse) {
		setDummyCookie(httpServletResponse);
		return "users/index";
	}
	
	private void setDummyCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("DummyCookie", "dummy_cookie");
		cookie.setMaxAge(259200);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
