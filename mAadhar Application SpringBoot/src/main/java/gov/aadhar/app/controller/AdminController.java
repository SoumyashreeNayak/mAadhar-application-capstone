package gov.aadhar.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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

import gov.aadhar.app.AadharCard;
import gov.aadhar.app.AdminUser;
import gov.aadhar.app.Citizen;
import gov.aadhar.app.service.AadharCardService;
import gov.aadhar.app.service.AdminService;
import gov.aadhar.app.service.CitizenService;
import gov.aadhar.app.service.WaitingCitizensEmailService;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	CitizenService citizenService;
	@Autowired
	WaitingCitizensEmailService waitingCitizenService;
	@Autowired
	AadharCardService aadharService;
	
	/*=========================================================================================//
	 *=============================ADMIN LOGIN/REGISTER/UPDATE OPERATIONS======================//
	 *=========================================================================================//
	 */
	
	//=============================REGISTER====================================================//
	@PostMapping("/")
	public String registerAdmin(@RequestBody AdminUser adminUser) {
		AdminUser testAdminUser=adminService.getAdminByEmail(adminUser.getEmail());
		if (testAdminUser!=null) return "0";
		if (adminService.addAdmin(adminUser) != null)
			return "1";
		else
			return "0";

	}
	//====================================GET ADMIN========================================//
	@GetMapping("/{email}")
	public AdminUser getAdminByEmail(@PathVariable String email) {
		return adminService.getAdminByEmail(email);
	}
	
	//=====================================UPDATE ADMIN DETAILS================================//
	@PutMapping("/{email}")
	public AdminUser updateAdminUser(@PathVariable String email, @RequestBody AdminUser adminUser) {
		AdminUser testAdminUser= adminService.getAdminByEmail(email);
		if (testAdminUser==null) return null;
		Long id=testAdminUser.getId();
		adminUser.setId(id);
		return(adminService.editAdmin(adminUser));
	}
	
	//======================================ADMIN LOGIN=========================================//
	@PutMapping("/login/{email}/{password}")
	public String adminLogIn(@PathVariable String email, @PathVariable String password) {
		System.out.println(email+"  "+password);
		AdminUser testAdminUser=adminService.getAdminByEmailAndPassword(email,password);
		if (testAdminUser==null)
				return "0";
		testAdminUser.setIsLoggedIn(true);
		adminService.editAdmin(testAdminUser);
		return "1";
	}
	
	//======================================ADMIN LOGOUT=========================================//
	@PutMapping("logout/{email}")
	public String adminLogOut(@PathVariable String email) {
		AdminUser testAdminUser=adminService.getAdminByEmail(email);
		if (testAdminUser==null)
				return "Oops!";
		testAdminUser.setIsLoggedIn(false);
		adminService.editAdmin(testAdminUser);
		return "Logged out!";
	}
	/*=========================================================================================//
	 *=============================CITIZEN USER OPERATIONS=====================================//
	 *=========================================================================================//
	 */
	@GetMapping("/waiting")
	public List<Citizen> getWaitingCitizens(){
		List<String> waitingCitizensEmail= waitingCitizenService.getWaitingCitizensEmail();
		List<Citizen> waitingCitizens=new ArrayList<>();
		for(String email:waitingCitizensEmail) {
			waitingCitizens.add(citizenService.getCitizenByEmail(email));
		}
		return waitingCitizens;
	}
	@PutMapping("/approve/{email}")
	public String approveCitizenAadhar(@PathVariable String email) {
		Citizen citizen=citizenService.getCitizenByEmail(email);
		if(citizen==null)
			return "Citizen not found!";
		String aadharNumber=aadharService.randomAadharNumberGenerator();
		
		while(isAadharNumberExists(aadharNumber)) {
			aadharNumber=aadharService.randomAadharNumberGenerator();
		}
		AadharCard aadharCard=new AadharCard();
		aadharCard.setId(aadharNumber);
		aadharCard.setIssueDate(LocalDate.now());
		aadharCard.setStatus("issued");		
		aadharCard.setCitizen(citizen);
		
		aadharService.addAadhar(aadharCard);
		citizen.setStatus("approved");
		
		citizenService.editCitizen(citizen);
		
		waitingCitizenService.removeWaitingCitizenEmail(email);
		return "Aadhar Number Allocated... Aadhar Number ="+aadharNumber+", Citizen Name ="+citizen.getName();
		
	}
	//===========METHOD TO CHECK IF AADHAR NUMBER HAS ALREADY BEEN ALLOCATED TO A CITIZEN====//
	public boolean isAadharNumberExists(String aadharNumber) {
		Citizen testCitizen=citizenService.getCitizenByAadharNumber(aadharNumber);
		return(testCitizen!=null);
	}
	
	
	
	
	@PutMapping("/reject/{email}")
	public String rejectCitizenAadhar(@PathVariable String email) {
		Citizen citizen=citizenService.getCitizenByEmail(email);
		if(citizen==null)
			return "Citizen not found!";
		citizen.setStatus("rejected");
		waitingCitizenService.removeWaitingCitizenEmail(email);
		return "Aadhar request rejected";
	}
	
	@PutMapping("/notAlive/{email}")
	public String updateDeadCitizen(@PathVariable String email) {
		Citizen citizen=citizenService.getCitizenByEmail(email);
		if(citizen==null)
			return "0";
		citizen.setIsAlive(false);
		return "1";
	}
	
}
