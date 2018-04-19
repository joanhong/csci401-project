package capstone.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.Project;
import capstone.model.users.Student;
import capstone.repository.ProjectsRepository;
import capstone.util.ProjectAssignment;

@Service
public class ProjectService {
	@Autowired
	ProjectsRepository repository;
	private ProjectAssignment maxIteration;
	private static String folder_name = "src/main/java/capstone/algorithm/real_data";
	private static int NUM_RANKED = 5; // number of projects that each student can rank
	public static Map<Double, ProjectAssignment> iterations = new HashMap<>();
	
	public String runAlgorithm(Vector<Project> projects, Vector<Student> students) {
		for (int iteration = 0; iteration < 30; iteration++) {
			 ProjectAssignment a = new ProjectAssignment(projects, students);
			 a.run(iteration, NUM_RANKED, folder_name);
			 double groupSatScore = a.algoSatScore;
			 iterations.put(groupSatScore, a);
		}

		Double maxScore = Collections.max(iterations.keySet());
		System.out.println("maxScore: " + maxScore);
		maxIteration = iterations.get(maxScore);
		
		System.out.println(maxIteration.JSONOutputWeb());
		return maxIteration.JSONOutputWeb();
	}
	
	public void save(Project project) {
		repository.save(project);
	}
	
	public List<Project> findAll() {
		return (List<Project>) repository.findAll();
	}
}
