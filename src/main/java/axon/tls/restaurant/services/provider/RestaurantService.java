package axon.tls.restaurant.services.provider;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import axon.tls.restaurant.entities.Restaurant;

public interface RestaurantService {
  public abstract void createRestaurant(Restaurant restaurant);
  
  public abstract Restaurant updateRestaurant(Long id, Restaurant restaurant);
  
  public abstract Restaurant deleteRestaurant(Long id);
  
  public abstract Collection<Restaurant> getAllRestaurants();
  
  public abstract Restaurant getRestaurantById(Long id);

  public abstract Restaurant disableRestaurant(Long id);
}
