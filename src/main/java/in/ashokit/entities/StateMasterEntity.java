package in.ashokit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "STATES_MASTER")
public class StateMasterEntity {

	@Id
	@Column(name = "STATE_ID")
	private Integer stateid;
	
	@Column(name = "STATE_NAME")
	private String statename;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryid;
}
