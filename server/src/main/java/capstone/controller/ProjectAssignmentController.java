package capstone.controller;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.service.ProjectAssignmentService;

@RestController
public class ProjectAssignmentController
{
	private ProjectAssignmentService maxAlgorithm;
	private static String folder_name = "src/main/java/capstone/algorithm/real_data";
	private static int NUM_RANKED = 5; // number of projects that each student can rank
	
	public static Map<Double, ProjectAssignmentService> algorithms = new HashMap<>();
	public static Map<Double, Integer> iterations = new HashMap<>();
	
	public ProjectAssignmentController() {
	}
  
	@GetMapping("/projects")
	@CrossOrigin(origins = "http://localhost:3000")
	public String projects()
	{
		// create directory for each iteration's output file
		File dir = new File(folder_name + "/iterations");
		dir.mkdir();
		
		// run algorithm 30 times
		for (int iteration = 0; iteration < 30; iteration++) {
			 ProjectAssignmentService algorithm = new ProjectAssignmentService();
			 algorithm.run(iteration, NUM_RANKED, folder_name);
			 double groupSatScore = algorithm.algoSatScore;
			 
			 algorithms.put(groupSatScore, algorithm);
			 iterations.put(groupSatScore, iteration);
		}

		Double maxScore = Collections.max(algorithms.keySet());
		maxAlgorithm = algorithms.get(maxScore);
		Double _maxScore = Collections.max(algorithms.keySet());
		Integer maxIteration = iterations.get(_maxScore);
		
		System.out.println("maxScore: " + maxScore + ". maxIteration: " + maxIteration + ".");
		
		System.out.println(maxAlgorithm.JSONOutputWeb());
		return maxAlgorithm.JSONOutputWeb();
	}
	
	/* This is how running the algorithm 30 times used to be implemented.
	 * public class AlgorithmIterator {
		private static String folder_name = "real_data";
		private static int NUM_RANKED = 5; // number of projects that each student can rank
		public static Map<Double, Integer> iterations = new HashMap<Double, Integer>();
			
		public static void main(String[] args) {
	
			// create directory for each iteration's output file
			File dir = new File("src/algorithm/" + folder_name + "/iterations");
			dir.mkdir();
			
			// run algorithm 30 times
			for (int iteration = 0; iteration < 30; iteration++) {
				double groupSatScore = new Algorithm(iteration, NUM_RANKED, folder_name).algoSatScore;
				iterations.put(groupSatScore, iteration);
			}
			
			Double maxScore = Collections.max(iterations.keySet());
			System.out.println("maxScore: " + maxScore);
			
			Integer maxIteration = iterations.get(maxScore);
			System.out.println("maxIteration: " + maxIteration);
		}
	}*/
	
}
