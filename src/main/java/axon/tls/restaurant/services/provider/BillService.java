package axon.tls.restaurant.services.provider;

import java.util.Collection;

import axon.tls.restaurant.entities.Bill;

public interface BillService {
	  public abstract Bill createBill(Bill Bill);
	  
	  public abstract Bill disableBill(Long id);
	  
	  public abstract Bill updateBill(Long id, Bill Bill);
	  
	  public abstract Bill getBillById(Long id);
	  
	  public abstract Bill getBillByDeskId(Long deskId);
	  
	  public abstract Collection<Bill> getAllPaidBillByYearAndRestaurantId(Integer year,Long restaurantId);
 
	
	
}
