package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	public Session findByHttpSessionId(String httpSessionId);
}
