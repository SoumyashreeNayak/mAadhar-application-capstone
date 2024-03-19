package gov.aadhar.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.aadhar.app.Citizen;
import gov.aadhar.app.repo.CitizenRepo;

@Service
public class CitizenService {
	@Autowired
	CitizenRepo repo;

	public Citizen getCitizenByEmail(String emailId) {
		return repo.getByEmailId(emailId);
	}

	public Citizen getCitizenByMobile(String mobileNo) {
		return repo.getByMobileNo(mobileNo);
	}

	public Citizen addCitizen(Citizen citizen) {
		return repo.save(citizen);
	}

	public List<Citizen> getAllCitizens() {
		return repo.findAll();
	}

	public Citizen getCitizenByEmailIdAndMobile(String email, String mobile) {
		return repo.getByEmailIdAndMobile(email,mobile);
	}

	public Citizen editCitizen(Citizen citizen) {
		return repo.save(citizen);
	}

	public Citizen getCitizenByAadharNumber(String aadharNumber) {
		return repo.getByAadharNumber(aadharNumber);
	}
}
