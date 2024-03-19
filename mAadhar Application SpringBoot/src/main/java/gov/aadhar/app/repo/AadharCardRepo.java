package gov.aadhar.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.aadhar.app.AadharCard;

public interface AadharCardRepo extends JpaRepository<AadharCard, Long>{

}
