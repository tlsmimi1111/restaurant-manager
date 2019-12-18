package axon.tls.restaurant.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import axon.tls.restaurant.entities.FoodItem;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.exception.ResourceNotFoundException;
import axon.tls.restaurant.repository.FoodItemRepository;
import axon.tls.restaurant.repository.RestaurantRepository;
import axon.tls.restaurant.services.provider.FoodItemService;

@Service
public class FoodItemServiceImpl implements FoodItemService{

	@Autowired 
	FoodItemRepository itemRepo;
	
	@Autowired
	RestaurantRepository restRep;
	
	@Override
	public FoodItem createFoodItem(FoodItem itemRequest) {
		FoodItem newFoodItem = new FoodItem();
		
		Restaurant restaurant = restRep.findByIdAndIsDisabled(itemRequest.getRestaurant().getId(), 0)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant with "+ itemRequest.getRestaurant().getId()+" not found"));
		
		newFoodItem.setName(itemRequest.getName());
		newFoodItem.setPrice(itemRequest.getPrice());
		newFoodItem.setRestaurant(restaurant);
		
		
		return itemRepo.save(newFoodItem);
	}

	@Override
	public FoodItem updateFoodItem(Long id, FoodItem updatedFoodItem) {
		FoodItem foodItem = itemRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item with "+id+" not found"));
		
		if(updatedFoodItem.getName()!=null) {
			foodItem.setName(updatedFoodItem.getName());
		}
		if(updatedFoodItem.getPrice()!=0) {
			foodItem.setPrice(updatedFoodItem.getPrice());
		}
		
		return null;
	}

	@Override
	public FoodItem deleteFoodItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodItem getFoodItemById(Long id) {
		FoodItem foodItem = itemRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item with "+id+" not found"));
		return foodItem;
	}

	@Override
	public Collection<FoodItem> getFoodItemByRestaurantId(Long restaurantId) {
	
		return itemRepo.findByRestaurantIdAndIsDisabled(restaurantId, 0);
		
	}

	@Override
	public FoodItem disableFoodItem(Long id) {
		FoodItem foodItem = itemRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item with "+id+" not found"));
		foodItem.setIsDisabled(1);
		
		return itemRepo.save(foodItem);
	}

	
}
