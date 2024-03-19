package gov.aadhar.app;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WaitingCitizensEmail {
	@Id
	private String emailId;

	@Override
	public String toString() {
		return emailId;
	}

}
