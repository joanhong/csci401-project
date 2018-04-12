package capstone.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class AdminConfiguration {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	public Date deliverableOneDate;
	public Date deliverableTwoDate;
	public Date deliverableThreeDate;
	public Date deliverableFourDate;
	public Date deliverableFiveDate;
	public Date deliverableSixDate;
	public Date deliverableSevenDate;
	public Long numRankedProjects;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDeliverableOneDate() {
		return deliverableOneDate;
	}
	public void setDeliverableOneDate(Date deliverableOneDate) {
		this.deliverableOneDate = deliverableOneDate;
	}
	public Date getDeliverableTwoDate() {
		return deliverableTwoDate;
	}
	public void setDeliverableTwoDate(Date deliverableTwoDate) {
		this.deliverableTwoDate = deliverableTwoDate;
	}
	public Date getDeliverableThreeDate() {
		return deliverableThreeDate;
	}
	public void setDeliverableThreeDate(Date deliverableThreeDate) {
		this.deliverableThreeDate = deliverableThreeDate;
	}
	public Date getDeliverableFourDate() {
		return deliverableFourDate;
	}
	public void setDeliverableFourDate(Date deliverableFourDate) {
		this.deliverableFourDate = deliverableFourDate;
	}
	public Date getDeliverableFiveDate() {
		return deliverableFiveDate;
	}
	public void setDeliverableFiveDate(Date deliverableFiveDate) {
		this.deliverableFiveDate = deliverableFiveDate;
	}
	public Date getDeliverableSixDate() {
		return deliverableSixDate;
	}
	public void setDeliverableSixDate(Date deliverableSixDate) {
		this.deliverableSixDate = deliverableSixDate;
	}
	public Date getDeliverableSevenDate() {
		return deliverableSevenDate;
	}
	public void setDeliverableSevenDate(Date deliverableSevenDate) {
		this.deliverableSevenDate = deliverableSevenDate;
	}
	public Long getNumProjectRankings() {
		return numRankedProjects;
	}
	public void setNumProjectRankings(Long numProjectRankings) {
		this.numRankedProjects = numProjectRankings;
	}
}
