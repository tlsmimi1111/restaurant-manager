package axon.tls.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.Bill;
import axon.tls.restaurant.models.BillState;

@Repository 
public interface BillRepository extends JpaRepository<Bill, Long> {
	Optional<Bill> findFirstByStateAndDeskId(BillState billState, Long deskId);	
	Optional<Bill> findByIdAndIsDisabled(Long id, Integer isDisabled);

}
