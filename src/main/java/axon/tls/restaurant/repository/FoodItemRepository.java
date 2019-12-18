package axon.tls.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
	
	List<FoodItem>findByRestaurantIdAndIsDisabled(Long retaurantId,Integer isDisabled);
	Optional<FoodItem> findByIdAndIsDisabled(Long id, Integer isDisabled);

	
	
}
