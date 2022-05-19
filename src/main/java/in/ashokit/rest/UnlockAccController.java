package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.Unlockacc;
import in.ashokit.service.UserManageService;

@RestController
public class UnlockAccController {

	@Autowired 
	private UserManageService userservice;
	
	@PostMapping("/unlock")
	public String unlockedacc(@RequestBody Unlockacc unlock){
		return userservice.unlockacc(unlock);
	}
}
