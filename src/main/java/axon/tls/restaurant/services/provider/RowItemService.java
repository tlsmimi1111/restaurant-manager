package axon.tls.restaurant.services.provider;

import java.util.Collection;

import axon.tls.restaurant.entities.RowItem;

public interface RowItemService {
	
	  public abstract RowItem createRowItem(RowItem RowItem);
	  
	  public abstract RowItem updateRowItem(Long id, RowItem RowItem);
	  
	  public abstract RowItem deleteRowItem(Long id);
	  
	  public abstract RowItem getRowItemById(Long id);
	  
	  public abstract Collection<RowItem> getRowItemByBillId(Long billId);

	  public abstract RowItem disableRowItem(Long id);
}
