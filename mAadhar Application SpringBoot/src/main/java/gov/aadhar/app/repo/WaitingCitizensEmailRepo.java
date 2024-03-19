package gov.aadhar.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.aadhar.app.WaitingCitizensEmail;

public interface WaitingCitizensEmailRepo extends JpaRepository<WaitingCitizensEmail, String> {

}
