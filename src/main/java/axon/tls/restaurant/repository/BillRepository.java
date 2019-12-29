package axon.tls.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.Bill;
import axon.tls.restaurant.models.BillState;

@Repository 
public interface BillRepository extends JpaRepository<Bill, Long> {
	Optional<Bill> findFirstByStateAndDeskId(BillState billState, Long deskId);	
	Optional<Bill> findByIdAndIsDisabled(Long id, Integer isDisabled);
	
//	 @Query("select p from Product p where year(p.shipmentDate) = ?1 and month(p.shipmentDate) = ?2")
//	  List<Product> findAllByShipmentDate(Integer year, Integer month);
	 
	 @Query("select b from Bill b where year(b.createdAt) =?1 and b.state = ?2 and b.desk.floor.restaurant.id=?3")
	 List<Bill> findAllBillByYear(Integer year,BillState state,Long restaurantId);

}
