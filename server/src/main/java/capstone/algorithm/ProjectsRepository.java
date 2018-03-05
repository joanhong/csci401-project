package capstone.algorithm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProjectsRepository extends JpaRepository<Project, Long> {
	
}
