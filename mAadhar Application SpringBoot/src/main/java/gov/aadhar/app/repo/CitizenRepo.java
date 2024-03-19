package gov.aadhar.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.aadhar.app.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {
	
	public Citizen getByEmailId(String emailId);
	
	public Citizen getByMobileNo(String mobileNo);

	@Query("SELECT user FROM Citizen user WHERE user.emailId=?1 AND user.mobileNo=?2")
	public Citizen getByEmailIdAndMobile(String email, String mobile);

	@Query("SELECT user FROM Citizen user JOIN user.aadharCard  ua WHERE ua.id=?1")
	public Citizen getByAadharNumber(String aadharNumber);

}
