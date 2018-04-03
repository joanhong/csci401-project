package capstone.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.Project;
import capstone.repository.ProjectsRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController 
{
	@Autowired
	private ProjectsRepository repository;
	
	public ProjectController(ProjectsRepository repository)
	{
		this.repository = repository;
//		encryptUserPasswords();
	}
	
	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Project> getProjects()
	{
		return (List<Project>) repository.findAll();
	}	
	
	/*private ProjectAssignmentService maxIteration;
	private static String folder_name = "src/main/java/capstone/algorithm/real_data";
	private static int NUM_RANKED = 5; // number of projects that each student can rank
	public static Map<Double, ProjectAssignmentService> iterations = new HashMap<>();
  
	@PostMapping("assignment")
	@CrossOrigin(origins = "http://localhost:3000")
	public String projectAssignment(@RequestBody Vector<Project> projects, @RequestBody Vector<Student> students)
	{
		for (int iteration = 0; iteration < 30; iteration++) {
			 ProjectAssignmentService a = new ProjectAssignmentService(projects, students);
			 a.run(iteration, NUM_RANKED, folder_name);
			 double groupSatScore = a.algoSatScore;
			 iterations.put(groupSatScore, a);
		}

		Double maxScore = Collections.max(iterations.keySet());
		System.out.println("maxScore: " + maxScore);
		maxIteration = iterations.get(maxScore);
		
		System.out.println(maxIteration.JSONOutputWeb());
		return maxIteration.JSONOutputWeb();
	}*/
}

	

