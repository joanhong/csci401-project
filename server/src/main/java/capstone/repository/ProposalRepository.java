package capstone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import capstone.model.Proposal;

@RepositoryRestResource
public interface ProposalRepository extends CrudRepository<Proposal, Long>{

	Proposal findById(Long id);
	
}
