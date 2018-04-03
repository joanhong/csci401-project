package capstone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import capstone.model.Deliverable;

@RepositoryRestResource
public interface DeliverableRepository extends CrudRepository<Deliverable, Long> {

	Iterable<Deliverable> findAllByProjectNumber(Long projectNumber);

}
