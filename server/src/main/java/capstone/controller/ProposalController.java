package capstone.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.Proposal;
import capstone.repository.ProposalRepository;

@RestController
@RequestMapping("/proposals")
public class ProposalController {
	
	@Autowired
	ProposalRepository proposalRepo;
	
	public ProposalController(ProposalRepository proposalRepo) {
		this.proposalRepo = proposalRepo;
	}
	
	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Proposal> getProposals() {
		return proposalRepo.findAll();
	}
	
 	@RequestMapping(value = "/save",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Proposal saveData(@RequestBody Proposal proposal)
	{
		System.out.println("Received HTTP POST");
		System.out.println(proposal);
		System.out.println(proposal.getProjectName());
		System.out.println(proposal.getProjectSize());
		System.out.println(proposal.getTechnologiesExpected());
		System.out.println(proposal.getBackgroundRequested());
		System.out.println(proposal.getProjectDescription());   
	   
	    proposalRepo.save(proposal);
		return proposal; //new ResponseEntity<Boolean>(uiRequestProcessor.saveData(a),HttpStatus.OK);
	}
}
