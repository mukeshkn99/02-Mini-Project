package in.ashokit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.UserManageService;

@RestController
public class ForgetPaasController {

	@Autowired
	private UserManageService userservice;
	
	@GetMapping("/forget/{email}")
	public String forgetpwd(@PathVariable String email) {
		return userservice.forgotpwd(email);
	}
}
