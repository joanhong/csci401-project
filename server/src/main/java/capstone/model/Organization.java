package capstone.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Organization {
	@Id @GeneratedValue Integer orgId;
	String organization;
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
}
