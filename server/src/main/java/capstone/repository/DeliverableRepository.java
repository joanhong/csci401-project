package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import capstone.model.Deliverable;

@RepositoryRestResource
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {

	Iterable<Deliverable> findAllByProjectNumber(Long projectNumber);

}
