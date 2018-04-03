package capstone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import capstone.model.Project;


@RepositoryRestResource
public interface ProjectsRepository extends CrudRepository<Project, Long> {
	
}
