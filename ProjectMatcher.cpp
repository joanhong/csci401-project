#include <cassert>
#include <iomanip>
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <math.h>

#include "ProjectMatcher.h"

using namespace std;

/*
Tentative skeleton code, will optimize based off testing
*/

ProjectMatcher::ProjectMatcher(vector<Project> _projects, vector<Student> _students) {
	srand(time(0));
	projects = _projects;
	students = _students;
}

void ProjectMatcher::run() {
	for (Project curr: projects) {
		for (int i = 0; i < curr.maxSize; i++) {
			Student s = getStudentWithMaxRanking(curr, students);//those have ranked currProject highest. Randomly break ties
			curr.members.push_back(s);
			students.erase(find(students.begin(), students.end(), s));
		}
	}
}

Student ProjectMatcher::getStudentWithMaxRanking(Project p, vector<Student> students) {
	vector<Student> possibleStudents;
	int highestRanking = projects.size();

	for (Student s: students) {
		if ((s.rankings.find(p.name) != s.rankings.end())) { //s did rank p
			if (s.rankings[p.name]==highestRanking) {
				possibleStudents.add(s);
			}
			else if (s.rankings[p.name]<highestRanking) { //better ranking
				highestRanking = s.rankings[p.name];
				possibleStudents.clear();
				possibleStudents.add(s);
			}
		}
	}

	int randIndex = rand() % possibleStudents.size();
	return possibleStudents[randIndex];
}




