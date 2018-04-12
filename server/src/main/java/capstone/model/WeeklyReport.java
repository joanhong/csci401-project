package capstone.model;

import java.util.Vector;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import capstone.model.users.Student;

@Entity
public class WeeklyReport extends Assignment
{
	@Id
	@GeneratedValue
	Long id;
	
	@MapsId("student_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Student student;
	
	@MapsId("project_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Project project;
	
	private Vector<Task> completeTasks;
	private Vector<Task> futureTasks;
}