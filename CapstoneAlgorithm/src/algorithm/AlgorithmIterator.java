package algorithm;

import java.io.*;
import java.util.*;

public class AlgorithmIterator {
	
	private static int NUM_RANKED = 3; // number of projects that each student can rank
	public static Map<Double, Integer> iterations = new HashMap<Double, Integer>();
	
	public static void main(String[] args) {

		// create directory for each iteration's output file
		File dir = new File("src/algorithm/iterations");
		dir.mkdir();
		
		// run algorithm 30 times
		for (int iteration = 0; iteration < 30; iteration++) {
			double groupSatScore = new Algorithm(iteration, NUM_RANKED).algoSatScore;
			iterations.put(groupSatScore, iteration);
		}
		
		Double maxScore = Collections.max(iterations.keySet());
		System.out.println("maxScore: " + maxScore);
		
		Integer maxIteration = iterations.get(maxScore);
		System.out.println("maxIteration: " + maxIteration);
	}
}
