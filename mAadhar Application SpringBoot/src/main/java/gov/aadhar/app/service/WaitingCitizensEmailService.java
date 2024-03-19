package gov.aadhar.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.aadhar.app.WaitingCitizensEmail;
import gov.aadhar.app.repo.WaitingCitizensEmailRepo;

@Service
public class WaitingCitizensEmailService {
@Autowired
WaitingCitizensEmailRepo repo;
	public boolean addWaitingCitizenEmail(WaitingCitizensEmail waitingCitizenEmail) {
		if(repo.save(waitingCitizenEmail)!=null)
			return true;
		return false;
	}
	public List<String> getWaitingCitizensEmail() {
		List<WaitingCitizensEmail>waitingList= repo.findAll();
		List<String> waitingEmailList=new ArrayList<>();
		for(WaitingCitizensEmail email: waitingList) {
			waitingEmailList.add(email.toString());
			System.out.println(email.toString());
		}
		return waitingEmailList;
	}
	public void removeWaitingCitizenEmail(String email) {
		repo.deleteById(email);
	}
	
}
