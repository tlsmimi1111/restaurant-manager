package axon.tls.restaurant.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import axon.tls.restaurant.entities.Desk;
import axon.tls.restaurant.entities.Floor;
import axon.tls.restaurant.entities.Restaurant;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long>{
	Optional<Desk> findByIdAndIsDisabled(Long id, Integer isDisabled);
	Page<Desk>findByFloorRestaurantId(Long retaurantId,Pageable pageable);
	Long countByFloorId(Long id);
	List<Desk>findByFloorRestaurantId(Long retaurantId);
}
