package gov.aadhar.app;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Citizen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate dob;
	private String address;
	private String emailId;
	private String mobileNo;
	private String gender;
	 @JsonIgnore
	@OneToOne(mappedBy = "citizen")
	private AadharCard aadharCard;
	private String status="pending";
	private Boolean isLoggedIn=false;
	private Boolean isAlive=true;
}
