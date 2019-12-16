package axon.tls.restaurant.services.provider;

import java.util.Collection;
import axon.tls.restaurant.entities.Desk;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import axon.tls.restaurant.entities.Restaurant;

public interface DeskService {
  public abstract void createDesk(Desk desk);
  
  public abstract Desk updateDesk(Long id, Desk desk);
  
  public abstract Desk deleteDesk(Long id);
  
  public abstract Page<Desk> getDeksByRestaurantId(Long restaurantId,int page, int size);
  
  
  public abstract Collection<Desk> getDeksByRestaurantIdRaw(Long restaurantId);
  
  public abstract Collection<Desk> getAllDesks();
  
  public abstract Desk getDeskById(Long id);

  public abstract Desk disableDesk(Long id);
}
