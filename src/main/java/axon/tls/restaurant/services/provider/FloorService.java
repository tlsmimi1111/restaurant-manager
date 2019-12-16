package axon.tls.restaurant.services.provider;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


import axon.tls.restaurant.entities.Floor;


public interface FloorService {
  public abstract void createFloor(Floor floor);
  
  public abstract Floor updateFloor(Long id, Floor floor);
  
  public abstract Floor deleteFloor(Long id);
  
  public abstract Collection<Floor> getAllFloors();
  
  public abstract Floor getFloorById(Long id);
  
  public abstract Collection<Floor> getFloorByRestaurantId(Long restaurantId);

  public abstract Floor disableFloor(Long id);
}
