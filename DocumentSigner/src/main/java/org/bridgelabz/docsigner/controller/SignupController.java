package org.bridgelabz.docsigner.controller;

import org.bridgelabz.docsigner.model.User;
import org.bridgelabz.docsigner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signupPage", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}

	@RequestMapping(value = "/signupPage", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, BindingResult result) {

		userService.addUser(user);
		return "login";
	}

}
