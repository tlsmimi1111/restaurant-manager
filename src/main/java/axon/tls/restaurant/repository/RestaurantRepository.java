package axon.tls.restaurant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import axon.tls.restaurant.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
	
	Optional<Restaurant> findByIdAndIsDisabled(Long id, Integer isDisabled);
	Optional<List<Restaurant>> findByUserIdAndIsDisabled(Long userId, Integer isDisabled);
}
