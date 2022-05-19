package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.Loginform;
import in.ashokit.service.UserManageService;

@RestController
public class LoginController {

	@Autowired
	private UserManageService userservice;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Loginform loginform) {
			String log=userservice.loginform(loginform);
			return new ResponseEntity<>(log,HttpStatus.CREATED);
			
	}
}
