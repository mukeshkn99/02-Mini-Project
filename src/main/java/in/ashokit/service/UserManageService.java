package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.Loginform;
import in.ashokit.binding.Regform;
import in.ashokit.binding.Unlockacc;

public interface UserManageService {
	
	public String loginform(Loginform login);
	
	public String emailcheck(String email);
	
	public Map<Integer,String> getcountry();

	public Map<Integer,String> getstate(Integer countryid);

	public Map<Integer,String> getcity(Integer stateid);

	
	public String signup(Regform reg);

	public String unlockacc(Unlockacc unlock);

	public String forgotpwd(String email);

}
