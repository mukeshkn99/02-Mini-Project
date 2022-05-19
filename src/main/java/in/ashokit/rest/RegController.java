package in.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.Regform;
import in.ashokit.service.UserManageService;

@RestController
public class RegController {

	@Autowired
	private UserManageService userservice;
	
	@GetMapping("/checkemail/{email}")
	public String checkemail(@PathVariable("email")String email) {
		return userservice.emailcheck(email);
	}
	
	@GetMapping("/country")
	public Map<Integer, String> country() {
		return userservice.getcountry();
	}
	
	@GetMapping("/state/{countryid}")
	public Map<Integer,String> state(@PathVariable("countryid")Integer countryid){
		return userservice.getstate(countryid);
	}
	@GetMapping("/city/{stateid}")
	public Map<Integer,String> city(@PathVariable("stateid")Integer stateid){
		return userservice.getcity(stateid);
		
	}
	@PostMapping("/register")
	public String registration(@RequestBody Regform reg) {
		return userservice.signup(reg);
		
	}
}
