package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import capstone.model.Proposal;

@RepositoryRestResource
public interface ProposalRepository extends JpaRepository<Proposal, Long>{
	
}
