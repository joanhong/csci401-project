package capstone.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import capstone.model.Deliverable;
import capstone.repository.DeliverableRepository;

@RestController
@RequestMapping("/deliverables")
public class DeliverableController {
	@Autowired
	private DeliverableRepository deliverableRepository;
	
	public DeliverableController() {
	}
	
	// @RequestMapping(value = "/info",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@PostMapping("/info")
	@CrossOrigin(origins = "http://localhost:3000")
	public void createDeliverableInfo(@RequestBody Deliverable deliverable) {
		System.out.println(deliverable.name + " " + deliverable.description);
		deliverableRepository.save(deliverable);
	}
	
	@PostMapping("/upload") 
	@CrossOrigin(origins = "http://localhost:3000")
	public void createDeliverable(@RequestParam("file") MultipartFile uploadfile){
		if (!uploadfile.isEmpty()) {
			try {
	            saveFile(Arrays.asList(uploadfile));
	        } catch (IOException e) {
	        		System.out.println(e.getMessage());
	        }
        }
	}
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:3000")
	public Iterable<Deliverable> getAllDeliverables() {
		return deliverableRepository.findAll();
	}
	
	@GetMapping("/{project_number}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Iterable<Deliverable> getDeliverable(@PathVariable("project_number") Long projectNumber) {
		return deliverableRepository.findAllByProjectId(projectNumber);
	}
	
	@PostMapping("/{project_number}/{deliverable_number}/approve")
	@CrossOrigin(origins = "http://localhost:3000")
	public void approveDeliverable(@PathVariable("project_number") Long projectNumber, @PathVariable("deliverable_number") Long deliverableNumber) {
		deliverableRepository.setStatusForId("Approved", deliverableNumber);
		System.out.println("Approved!");
	}
	
	@PostMapping("/{project_number}/{deliverable_number}/reject")
	@CrossOrigin(origins = "http://localhost:3000")
	public void rejectDeliverable(@PathVariable("project_number") Long projectNumber, @PathVariable("deliverable_number") Long deliverableNumber) {
		deliverableRepository.setStatusForId("Rejected", deliverableNumber);
		System.out.println("Rejected :(");
	}
	
	private void saveFile(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
        		System.out.println("File received " + file.getOriginalFilename());
            if (!file.isEmpty()) {
            		byte[] bytes = file.getBytes();
            }
        }
    }
}
