package axon.tls.restaurant.services.provider;

import java.util.Collection;

import axon.tls.restaurant.entities.FoodItem;
import axon.tls.restaurant.models.FoodItemRequest;

public interface FoodItemService {
	public abstract FoodItem createFoodItem(FoodItem FoodItem,Long restaurantId);
	  
	  public abstract FoodItem updateFoodItem(Long id, FoodItem FoodItem);
	  
	  public abstract FoodItem deleteFoodItem(Long id);
	  
	  public abstract FoodItem getFoodItemById(Long id);
	  
	  public abstract Collection<FoodItem> getFoodItemByRestaurantId(Long restaurantId);

	  public abstract FoodItem disableFoodItem(Long id);
}
