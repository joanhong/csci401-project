package capstone.algorithm;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmController
{
	private Algorithm maxIteration;
	private static String folder_name = "src/main/java/capstone/algorithm/real_data";
	private static int NUM_RANKED = 5; // number of projects that each student can rank
	public static Map<Double, Algorithm> iterations = new HashMap<>();
	
	public AlgorithmController() {
	}
  
	@GetMapping("/projects")
	@CrossOrigin(origins = "http://localhost:3000")
	public String projects()
	{
		//if (maxIteration == null) {
			// run algorithm 30 times
			for (int iteration = 0; iteration < 30; iteration++) {
				 Algorithm a = new Algorithm(iteration, NUM_RANKED, folder_name);
				 double groupSatScore = a.algoSatScore;
				 iterations.put(groupSatScore, a);
			}

			Double maxScore = Collections.max(iterations.keySet());
			System.out.println("maxScore: " + maxScore);
			maxIteration = iterations.get(maxScore);
		//}
		System.out.println(maxIteration.JSONOutputWeb());
		return maxIteration.JSONOutputWeb();
	}
}
