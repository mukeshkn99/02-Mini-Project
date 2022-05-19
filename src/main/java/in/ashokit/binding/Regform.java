package in.ashokit.binding;

import java.time.LocalDate;


import lombok.Data;

@Data
public class Regform {

	private String firstname;

	private String lastname;

	private String email;

	private Long phno;

	private LocalDate dob;

	private String gender;

	private Integer countryid;

	private Integer stateid;

	private Integer cityid;

}
