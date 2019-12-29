package axon.tls.restaurant.services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.math.Quantiles;

import axon.tls.restaurant.entities.Bill;
import axon.tls.restaurant.entities.Desk;
import axon.tls.restaurant.entities.DeskState;
import axon.tls.restaurant.entities.FoodItem;
import axon.tls.restaurant.entities.RowItem;
import axon.tls.restaurant.exception.ResourceNotFoundException;
import axon.tls.restaurant.models.BillState;
import axon.tls.restaurant.repository.BillRepository;
import axon.tls.restaurant.repository.DeskRepository;
import axon.tls.restaurant.services.provider.BillService;
import axon.tls.restaurant.services.provider.DeskService;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepository billRepo;
	
	@Autowired
	DeskRepository deskRepo;
	
	@Autowired
	DeskService deskService;
	
	@Override
	public Bill createBill(Bill bill) {
		Bill newBill = new Bill();
		
		Desk desk = deskRepo.findByIdAndIsDisabled(bill.getDesk().getId(), 0)
				.orElseThrow(() -> new ResourceNotFoundException("desk with "+ bill.getDesk().getId()+" not found"));
		
		
		newBill.setDesk(desk);
		newBill.setState(BillState.UNPAID);
		
		Desk occupiedDesk = new Desk();
		occupiedDesk.setState(DeskState.OCCUPIED);
		deskService.updateDesk(bill.getDesk().getId(), occupiedDesk);
		
		return billRepo.save(newBill);
	}
	
	@Override
	public Bill getBillById(Long id) {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		
		bill = billRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("bill "+id+"not found"));
		
		int total = 0;
		for (RowItem rowItem : bill.getRowItems()) {
			int rowItemMoney = rowItem.getItem().getPrice() * rowItem.getQuantity();
			total+= rowItemMoney;
		}
		bill.setTotal(total);
		return bill;
	}
	
	@Override
	public Bill updateBill(Long id, Bill billRequest) {
		Bill bill = billRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("bill with"+id+"not found"));
		int total = 0;
		if(bill == null) {
			return null;
		}
		
		if(billRequest.getState() == BillState.PAID) {
			Desk occupiedDesk = new Desk();
			occupiedDesk.setState(DeskState.AVAILABLE);
			deskService.updateDesk(bill.getDesk().getId(), occupiedDesk);
			for (RowItem rowItem : bill.getRowItems()) {
				FoodItem foodItem = rowItem.getItem();
				total += (foodItem.getPrice()* rowItem.getQuantity());
			}
			bill.setTotal(total);
			}
	
		bill.setState(billRequest.getState());
		
		return billRepo.save(bill);
		
	}

	@Override
	public Collection<Bill> getAllPaidBillByYearAndRestaurantId( Integer year,Long restaurantId) {
		
		// TODO Auto-generated method stub
		return billRepo.findAllBillByYear(year,BillState.PAID, restaurantId);
	}

	@Override
	public Bill disableBill(Long id) {
		Bill bill = billRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("bill with"+id+"not found"));

		if(bill == null) {
			return null;
		}
		
		bill.setIsDisabled(1);
		
		
		return billRepo.save(bill);
	}

	@Override
	public Bill getBillByDeskId(Long deskId) {
		// TODO Auto-generated method stub
		Bill bill =  billRepo.findFirstByStateAndDeskId(BillState.UNPAID, deskId).orElseThrow(()-> new ResourceNotFoundException("bill with"+deskId+"not found"));
		if(bill != null ) {
			int total = 0;
			for (RowItem rowItem : bill.getRowItems()) {
				int rowItemMoney = rowItem.getItem().getPrice() * rowItem.getQuantity();
				total+= rowItemMoney;
			}
			bill.setTotal(total);
		}
		return bill;
	}
}
