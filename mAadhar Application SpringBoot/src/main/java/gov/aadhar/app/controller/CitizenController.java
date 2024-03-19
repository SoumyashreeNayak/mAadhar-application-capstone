package gov.aadhar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.aadhar.app.Citizen;
import gov.aadhar.app.WaitingCitizensEmail;
import gov.aadhar.app.service.CitizenService;
import gov.aadhar.app.service.WaitingCitizensEmailService;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/citizen")
public class CitizenController {
	@Autowired
	CitizenService service;
	@Autowired
	WaitingCitizensEmailService waitingCitizenService;

	@PostMapping("/")
	public String registerCitizen(@RequestBody Citizen citizen) {
		Citizen testCitizen1 = service.getCitizenByEmail(citizen.getEmailId());
		Citizen testCitizen2 = service.getCitizenByMobile(citizen.getMobileNo());
		if (testCitizen1!= null)
			return "e1";
		
		else if (testCitizen2 != null)
			return "e2";


		if (service.addCitizen(citizen) != null) {
			WaitingCitizensEmail waitingCitizenEmail=new WaitingCitizensEmail();
			waitingCitizenEmail.setEmailId(citizen.getEmailId());
			if (waitingCitizenService.addWaitingCitizenEmail(waitingCitizenEmail))
			return "1";
			else return "e";
		}else
			return "e";
	}

	@GetMapping("/")
	public List<Citizen> getAllCitizens() {
		return service.getAllCitizens();
	}

	@GetMapping("/email/{email}")
	public Citizen getCitizenByEmail(@PathVariable String email) {
		return service.getCitizenByEmail(email);
	}

	@GetMapping("/mobile/{mobile}")
	public Citizen getCitizenByMobile(@PathVariable String mobile) {
		return service.getCitizenByMobile(mobile);
	}

	@PutMapping("/{email}/{mobile}")
	public Citizen updateCitizen(@PathVariable String email, @PathVariable String mobile,
			@RequestBody Citizen citizen) {
		Citizen testCitizen = service.getCitizenByEmailIdAndMobile(email, mobile);
		if(testCitizen==null) return null;
		Long id=testCitizen.getId();
		citizen.setId(id);
		return(service.editCitizen(citizen));
				
	}

	@PutMapping("/login/{email}/{password}")
	public String citizenLogIn(@PathVariable String email, @PathVariable String password) {
		Citizen testCitizen=service.getCitizenByEmailIdAndMobile(email,password);
		//Mobile number acts as Password
		if (testCitizen==null)
				return "0";
		testCitizen.setIsLoggedIn(true);
		service.editCitizen(testCitizen);
		return "1";
	}
	@GetMapping("/aadharStatus/{email}/{password}")
	public String checkAadharStatus(@PathVariable String email, @PathVariable String password) {
		Citizen testCitizen=service.getCitizenByEmailIdAndMobile(email,password);
		if(testCitizen==null)
			return "invalid credentials";
		switch(testCitizen.getStatus()) {
		case "pending":default: return "Your aadhar status is still pending... Check back later"; 
		case "approved": return "Your aadhar card has been approved.\r Your Aadhar Number : "+testCitizen.getAadharCard().getId();
		case "rejected": return "Your aadhar application has been rejected... Please contact nearest Aadhar Center";
		}
		
		
	}
	@PutMapping("/logout/{email}")
	public String citizenLogOut(@PathVariable String email) {
		Citizen testCitizen=service.getCitizenByEmail(email);
		if (testCitizen==null)
				return "Oops!";
		testCitizen.setIsLoggedIn(false);
		service.editCitizen(testCitizen);
		return "Logged out!";
	}

}
