package axon.tls.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.RowItem;

@Repository
public interface RowItemRepository extends JpaRepository<RowItem,Long>{
	
	List<RowItem> findAllByBillIdAndIsDisabled(Long billId, Integer isDisabled );
	Optional<RowItem> findByIdAndIsDisabled(Long id, Integer isDisabled);
	
}	
