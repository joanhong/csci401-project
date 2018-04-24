package capstone.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.Project;
import capstone.model.users.Stakeholder;
import capstone.model.users.Student;
import capstone.model.users.User;
import capstone.service.ProjectService;
import capstone.service.UserService;
import capstone.util.Constants;

@RestController
@RequestMapping("/projects")
public class ProjectController 
{
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	
	
	public ProjectController()
	{
	}
	@GetMapping("/init")
	@CrossOrigin(origins = "http://localhost:3000")
	public void initTables() {
		projectService.initTables();
	}
	
	@GetMapping("")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Project> getProjects()
	{
		return projectService.findAll();
	}	

	@GetMapping("/assignment")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Project> projectAssignment()
	{
		System.out.println("running assignment");
		/*List<Project> existing = projectService.getExistingAssignments();
		if (existing != null && existing.size() > 0) {
			return existing;
		}*/
		return projectService.runAlgorithm();
		//return projectService.runAlgorithm(getProjects(), (List<Student>)userService.getStudents());
	}
	
	@GetMapping("/assignment/exists")
	public String assignmentExists() {
		List<Project> existing = projectService.getExistingAssignments();
		if (existing != null && existing.size() > 0) {
			return "true";
		}
		return "false";
	}
	
	@GetMapping("/{email:.+}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Project> getProjectsByEmail(@PathVariable("email") String email) {
		Stakeholder user = userService.findStakeholderByEmail(email);
		List<Project> projects = userService.getStakeholderProjects(user);
		return projects;
	}
	
	@GetMapping("/{email:.+}/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Project getProjectByEmailAndId(@PathVariable("email") String email,
			@PathVariable("projectId") Long projectId) {
		Stakeholder user = userService.findStakeholderByEmail(email);
		List<Project> projects = userService.getStakeholderProjects(user);
		for (Project project : projects) {
			if (project.getProjectId() == projectId) {
				return project;
			}
		}
		return null;
	}
	
	@PostMapping("/save/{email:.+}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Project saveData(@PathVariable("email") String email, 
			@RequestBody Project project)
	{
		System.out.println("Received HTTP POST");
		System.out.println(project);
		System.out.println(project.getProjectName());
		project.setStatusId(1);
		User user = userService.findUserByEmail(email);
	    projectService.save(project);
	    userService.saveProject(user, project);
		return project; //new ResponseEntity<Boolean>(uiRequestProcessor.saveData(a),HttpStatus.OK);
	}
	
	@PostMapping("/rankingsSubmitAttempt/{email:.+}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String projectRankingsSubmission(@PathVariable("email") String email, @RequestBody List<Integer> projects) {
		User user = userService.findUserByEmail(email);
		for (int rank = 1; rank <= 5; rank++) {
			projectService.saveRanking(projects.get(rank-1), user.getUserId(), rank);
		}
		return Constants.SUCCESS;
	}
	
	@PostMapping("/assignToStudents")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String assignProjectsToStudents(@RequestBody ArrayList<Project> projects) {
		for (Project project : projects) {
			//projectService.save(project);
			if (project.getProjectId() > 0) {
				Project saveProject = projectService.findByProjectId(project.getProjectId());
				//List<Student> saveMembers = new ArrayList<Student>();
				for (Student student : project.getMembers()) {
					Student saveStudent = userService.findByUserId(student.getUserId());
					//saveMembers.add(saveStudent);
					saveStudent.setProject(project);
					userService.saveUser(saveStudent);
				}
				//saveProject.setMembers(saveMembers);
			}
		}
		projectService.saveAssignment(projects);
		return Constants.SUCCESS;
	}
	
	@GetMapping("/user/{email:.+}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Project getUserProject(@PathVariable("email") String email) {
		Student user = (Student) userService.findUserByEmail(email);
		System.out.println(user.getProject().getProjectName());
		return user.getProject();
	}
	
	@GetMapping("/students/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody List<User> getAllStudentsOnProject(@PathVariable("projectId") int projectId) {
		return userService.findAllByProject(projectService.findByProjectId(projectId));
	}
	
	@GetMapping("/stakeholder/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody User getStakeholderOnProject(@PathVariable("projectId") int projectId) {
		List<Stakeholder> stakeholders = (List<Stakeholder>) userService.getStakeholders();
		for (Stakeholder s : stakeholders) {
			for (Project p : s.getProjectIds()) {
				if (p.getProjectId() == projectId) {
					return s;
				}
			}
		}
		return null;
	}
	
	@PostMapping("/pending/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String pendingProjects(@PathVariable("projectId") int projectId) {
		Project project = projectService.findByProjectId(projectId);
		project.setStatusId(1);
		projectService.save(project);
		return Constants.SUCCESS;
	}
	
	@PostMapping("/approve/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String approveProjects(@PathVariable("projectId") int projectId) {
		Project project = projectService.findByProjectId(projectId);
		project.setStatusId(2);
		projectService.save(project);
		return Constants.SUCCESS;
	}
	
	@PostMapping("/reject/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String rejectProjects(@PathVariable("projectId") int projectId) {
		Project project = projectService.findByProjectId(projectId);
		project.setStatusId(3);
		projectService.save(project);
		return Constants.SUCCESS;
	}
	
	@PostMapping("/change/{projectId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String requestChangeProjects(@PathVariable("projectId") int projectId) {
		Project project = projectService.findByProjectId(projectId);
		project.setStatusId(4);
		projectService.save(project);
		return Constants.SUCCESS;
	}
	
	
}

	

