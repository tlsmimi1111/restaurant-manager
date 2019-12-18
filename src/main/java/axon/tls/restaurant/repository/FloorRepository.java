package axon.tls.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.Floor;
import axon.tls.restaurant.entities.Restaurant;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long>{
	Optional<Floor> findByIdAndIsDisabled(Long id, Integer isDisabled);
	List<Floor> findAllByRestaurantIdAndIsDisabled(Long restaurantId, Integer isDisabled);
}
