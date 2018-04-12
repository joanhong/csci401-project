package capstone.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import capstone.model.users.User;

@Entity
public class Session {
	public Session(HttpSession session, User user) {
		this.httpSession = session;
		this.httpSessionId = session.getId();
		this.userId = user.getId();
	}

	@Id
	@GeneratedValue
	Long id;
	
	@Transient
	private HttpSession httpSession;
	
	private String httpSessionId;
	
	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public String getHttpSessionId() {
		return httpSessionId;
	}

	public void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}

	//@MapsId("user_id")
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HttpSession getSession() {
		return httpSession;
	}

	public void setSession(HttpSession session) {
		this.httpSession = session;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
