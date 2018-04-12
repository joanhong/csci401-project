package capstone.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import capstone.model.users.Student;

@Entity
public class PeerReview extends Assignment
{
	@Id
	@GeneratedValue
	Long id;
	
	@MapsId("project_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Project project;
	
	@MapsId("student_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Student student;
	
	@MapsId("reviewed_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Student studentReviewed;
	
	private String positiveFeedback;
	private String negativeFeedback;
	
}
