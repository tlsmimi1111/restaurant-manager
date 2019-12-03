package axon.tls.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.Desk;
import axon.tls.restaurant.entities.Restaurant;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long>{
	Optional<Desk> findByIdAndIsDisabled(Long id, Integer isDisabled);
	
	Long countByFloorId(Long id);
}
