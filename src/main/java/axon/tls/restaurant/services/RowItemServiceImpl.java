package axon.tls.restaurant.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import axon.tls.restaurant.entities.Bill;
import axon.tls.restaurant.entities.FoodItem;
import axon.tls.restaurant.entities.RowItem;
import axon.tls.restaurant.exception.ResourceNotFoundException;
import axon.tls.restaurant.repository.BillRepository;
import axon.tls.restaurant.repository.FoodItemRepository;
import axon.tls.restaurant.repository.RowItemRepository;
import axon.tls.restaurant.services.provider.RowItemService;

@Service
public class RowItemServiceImpl implements RowItemService {
	
	@Autowired
	RowItemRepository rowItemRepo;

	@Autowired
	FoodItemRepository foodItemRepo;
	
	@Autowired
	BillRepository billRepo;

	@Override
	public RowItem createRowItem(RowItem rowItem) {
		RowItem newRowItem = new RowItem();
		
		FoodItem item = foodItemRepo.findByIdAndIsDisabled(rowItem.getItem().getId(), 0)
				.orElseThrow(() -> new ResourceNotFoundException("item with "+ rowItem.getItem().getId()+" not found"));
		
		Bill bill = billRepo.findByIdAndIsDisabled(rowItem.getBill().getId(), 0)
				.orElseThrow(() -> new ResourceNotFoundException("Bill with "+ rowItem.getBill().getId()+" not found"));
		
		newRowItem.setItem(item);
		newRowItem.setBill(bill);
		newRowItem.setQuantity(rowItem.getQuantity());
		
		
		return rowItemRepo.save(newRowItem);
	}
	
	@Override
	public RowItem getRowItemById(Long id) {
		// TODO Auto-generated method stub
		RowItem rowItem = new RowItem();
		
		rowItem = rowItemRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("row item"+id+"not found"));
		
		return rowItem;
	}
	
	@Override
	public RowItem disableRowItem(Long id) {
		RowItem rowItem = rowItemRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("row item"+id+"not found"));

		if(rowItem == null) {
			return null;
		}
		
		rowItem.setIsDisabled(1);
		
		
		return rowItemRepo.save(rowItem);
	}
	
	@Override
	public RowItem deleteRowItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowItem updateRowItem(Long id, RowItem rowItemRequest) {
		RowItem rowItem = rowItemRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("row item"+id+"not found"));
		
		if(rowItem == null) {
			return null;
		}
		
		rowItem.setQuantity(rowItemRequest.getQuantity()==0?rowItemRequest.getQuantity():rowItemRequest.getQuantity());
		
		return rowItemRepo.save(rowItem);
		
	}



	@Override
	public Collection<RowItem> getRowItemByBillId(Long billId) {
	
		return rowItemRepo.findAllByBillIdAndIsDisabled(billId, 0);
	}

}
