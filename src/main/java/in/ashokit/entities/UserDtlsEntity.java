package in.ashokit.entities;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name= "USER_DETLS")
@Data
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "USER_ID")
	private Integer userid;
	
	@Column(name= "FIRST_NAME")
	private String firstname;
	
	@Column(name= "LAST_NAME")
	private String lastname;
	
	@Column(name= "USER_EMAIL", unique= true)
    private String email;
	
	@Column(name= "USER_PWD")
    private String password;
	
	@Column(name= "PHNO")
    private Long phno;
	
	@Column(name= "DOB")
    private LocalDate dob;
	
	@Column(name= "GENDER")
    private String gender;
	
	@Column(name= "CITY_ID")
    private Integer cityid;
	
	@Column(name= "COUNTRY_ID")
    private Integer countryid;
	
	@Column(name= "STATE_ID")
    private Integer stateid;
	
	@Column(name="Acc_Status")
    private String accstatus;
	
	@Column(name= "create_date",updatable=false)
	@CreationTimestamp
    private LocalDate createdate;
	
	@Column(name= "update_date",insertable=false)
	@CreationTimestamp
    private LocalDate updatedate;
    
}
