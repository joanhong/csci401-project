#ifndef PROJECT_MATCHER
#define PROJECT_MATCHER

#include <vector>
#include <map>

using namespace std;

struct Student {
	map<string, int> rankings; //maps project name to ranking
};

struct Project {
	string name;
	int priority;
	int maxSize;
	int minSize;
	vector<Student> members;
};

class ProjectMatcher {
public:
	DecisionTreeSolver(vector<Project> _projects, vector<Student> _students);
	void run();

private:
	vector<Project> _projects;
	vector<Student> _students;

	
};

#endif
