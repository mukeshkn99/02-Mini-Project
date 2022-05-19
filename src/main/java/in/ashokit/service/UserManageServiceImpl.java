package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.Loginform;
import in.ashokit.binding.Regform;
import in.ashokit.binding.Unlockacc;
import in.ashokit.entities.CityMasterEntity;
import in.ashokit.entities.CountryMasterEntity;
import in.ashokit.entities.StateMasterEntity;
import in.ashokit.entities.UserDtlsEntity;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserRepository;
import in.ashokit.utils.EmailUtils;

@Service
public class UserManageServiceImpl implements UserManageService{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private CountryRepository countryrepo;
	
	@Autowired
	private StateRepository staterepo;
	
	@Autowired
	private CityRepository cityrepo;
    
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public String loginform(Loginform login) {
           UserDtlsEntity entity=userrepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		   
		   if(entity==null) {
			   return"Invalid Credentials";
		   }
		   if(entity!=null && entity.getAccstatus().equals("LOCKED")) {
			   return "Account is Locked";
		   }
           return "success";
		
	}
	@Override
	public String emailcheck(String email) {
		UserDtlsEntity entity=userrepo.findByEmail(email);
		if(entity==null) {
			return "UNIQUE";
		}
		return "Duplicate";
	}

	@Override
	public Map<Integer, String> getcountry() {
		List<CountryMasterEntity> countries=countryrepo.findAll();
		Map<Integer,String> countrymap=new HashMap<>();
		for(CountryMasterEntity country:countries) {
			countrymap.put(country.getCountryid(),country.getCountryname());
		}
		return countrymap;
	}

	@Override
	public Map<Integer, String> getstate(Integer countryid) {
		List<StateMasterEntity> states=staterepo.findByCountryid(countryid);
		Map<Integer,String> statemap=new HashMap<>();
		for(StateMasterEntity state:states) {
			statemap.put(state.getStateid(), state.getStatename());
		}
		return statemap;
	}

	@Override
	public Map<Integer, String> getcity(Integer stateid) {
	List<CityMasterEntity> cities=cityrepo.findByStateid(stateid);
	Map<Integer,String> citymap=new HashMap<>();
	for(CityMasterEntity city:cities) {
		citymap.put(city.getCityid(),city.getCityname());
	}
		return citymap;
	}

	@Override
	public String signup(Regform reg) {
		UserDtlsEntity entity=new UserDtlsEntity();
		BeanUtils.copyProperties(reg, entity);
		
		entity.setAccstatus("LOCKED");
		
		entity.setPassword(Randompwd()); 
		
		UserDtlsEntity savedentity=userrepo.save(entity);
		
		String email=reg.getEmail();
		String subject="User-Registered";
		String filename="UNLOCK-ACCOUNT-EMAIL-BODY.txt";
		String body=readmailbody(filename,entity);
		
		boolean isSent=emailutils.sendmail(email, subject, body);
		if(savedentity.getUserid()!=null && isSent) {
			return "SUCCESS";
		}
		return "FAIL";
	}

	
	@Override
	public String unlockacc(Unlockacc unlock) {
		if(!(unlock.getNewpassword().equals(unlock.getConfirmpassword()))) {
			return "confirmpassword and newpassword should be same";
		}
		UserDtlsEntity entity=userrepo.findByEmailAndPassword(unlock.getEmail(), unlock.getTemppassword());
		if(entity==null) {
			return "password is incorrect";
		}
		entity.setPassword(unlock.getNewpassword());
		entity.setAccstatus("UNLOCKED");
		
		userrepo.save(entity);
		return "success";
	}

	@Override
	public String forgotpwd(String email) {
		UserDtlsEntity entity=userrepo.findByEmail(email);
		if(entity==null) {
			return "Invalid Emailid";
		}
		String subject="Password Changed";
		String filename="Recover-Password.txt";
		String body=readmailbody(filename,entity);
		
		boolean isSent=emailutils.sendmail(email, subject, body);
		if(isSent) {
			return "Password Sent Email"; 
		}
		
		return "FAIL";
	}
	
	public String Randompwd() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	    private String readmailbody(String filename, UserDtlsEntity entity) {
			String mailbody=null;
			try {
				StringBuffer sb=new StringBuffer();
				FileReader fr=new FileReader(filename);
				BufferedReader br=new BufferedReader(fr);
				String line=br.readLine();
				while(line!=null) {
					sb.append(line);
					line=br.readLine();
				}
				mailbody=sb.toString();
				mailbody=mailbody.replace("{FNAME}", entity.getFirstname());
				mailbody=mailbody.replace("{LNAME}", entity.getLastname());
				mailbody=mailbody.replace("{TEMP-PWD}", entity.getPassword());
				mailbody=mailbody.replace("{EMAIL}", entity.getEmail());
				mailbody=mailbody.replace("{PWD}", entity.getPassword());
				br.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return mailbody;
		}
	}
	


