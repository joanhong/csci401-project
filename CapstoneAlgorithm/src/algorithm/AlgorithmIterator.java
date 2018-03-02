package algorithm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlgorithmIterator {
	
	private static String folder_name = "input_data";
	private static int NUM_RANKED = 5; // number of projects that each student can rank
	public static Map<Double, Integer> iterations = new HashMap<Double, Integer>();
	
	private Vector<Project> projects;
	private Vector<Student> students;
	
	public static int getStudentSatScore(int i) { // i = project's rank
		return ( ( (NUM_RANKED-i+1) * (NUM_RANKED-i)) / 2 ) + 1;
	}
	
	public AlgorithmIterator(Vector<File> projectFiles, Vector<File> rankingFiles) {
		
		projects = new Vector<Project>();
		students = new Vector<Student>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		for (File file : projectFiles) {
			
			/* MAP IMPLEMENTATION */
			
			// read JSON data from file:
			byte[] jsonData = null;
			try {
				jsonData = Files.readAllBytes(Paths.get(file.getName()));
			} catch (IOException e) { e.printStackTrace(); }
			
			// put JSON data into a HashMap:
			Map<String,String> projectJsonMap = new HashMap<String, String>();
			try {
				projectJsonMap = mapper.readValue(jsonData, HashMap.class);
			} catch (IOException e) { e.printStackTrace(); }
			System.out.println("Project Map: " + projectJsonMap);

			// create new Project from data:
            Project newProject = new Project(getStudentSatScore(1));
            newProject.name = projectJsonMap.get("name");
            newProject.projectId = projects.size();
            newProject.minSize = Integer.parseInt(projectJsonMap.get("minSize"));
            newProject.maxSize = Integer.parseInt(projectJsonMap.get("maxSize"));
            projects.addElement(newProject);
						
		}
		
		for (File file : rankingFiles) {
			
			// read JSON data from file:
			byte[] jsonData = null;
			try {
				jsonData = Files.readAllBytes(Paths.get(file.getName()));
			} catch (IOException e) { e.printStackTrace(); }
			
			
			// access JSON's root node:
			JsonNode rootNode = null;
			try {
				rootNode = mapper.readTree(jsonData);
			} catch (IOException e) { e.printStackTrace(); }
			
			
			// create new Student:
            Student newStudent = new Student();
			int studentId = rootNode.path("student").asInt();
//          newStudent.studentId = students.size(); // ??? TODO 
			
			// for the student's top NUM_RANKED projects:
			Iterator<JsonNode> rankings = rootNode.path("rankings").elements();
			while(rankings.hasNext()) {
				JsonNode ranking = rankings.next();
				int projectId = ranking.path("projectId").asInt();
				int rank = ranking.path("rank").asInt();
				
				Project rankedProject = projects.elementAt(projectId - 1); // TODO !!! SUBTRACT 1, as the ranking's indices skip 0 for readability
        			
				// add rankedProject to the Student data structure:
                String projectName = rankedProject.name;
                newStudent.rankings.put(projectName, rank);
                newStudent.orderedRankings.addElement(projectName);
                
                // popularity metrics:
                Integer p = getStudentSatScore(rank);
                rankedProject.sum_p += p;
                rankedProject.n += 1;     
			}
			
			students.addElement(newStudent);

		}

	}
	
	public static void run() {
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
	
	public static void main(String[] args) {
		run();
	}

}
