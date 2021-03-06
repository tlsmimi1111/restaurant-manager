package axon.tls.restaurant.services;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import axon.tls.restaurant.entities.Desk;
import axon.tls.restaurant.entities.DeskState;
import axon.tls.restaurant.entities.Floor;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.entities.User;
import axon.tls.restaurant.exception.BadRequestException;
import axon.tls.restaurant.exception.ResourceNotFoundException;
import axon.tls.restaurant.repository.DeskRepository;
import axon.tls.restaurant.repository.FloorRepository;
import axon.tls.restaurant.repository.RestaurantRepository;
import axon.tls.restaurant.repository.UserRepository;
import axon.tls.restaurant.services.provider.DeskService;
import axon.tls.restaurant.services.provider.RestaurantService;

@Service
public class DeskServiceImpl implements DeskService {

	@Autowired
	DeskRepository deskRepo;
	
	@Autowired
	FloorRepository floorRepo;

	@Override
	public Desk createDesk(Desk desk) {
		Desk newDesk = new Desk();
		String deskName;
		
		Floor floor = floorRepo.findByIdAndIsDisabled(desk.getFloor().getId(), 0)
				.orElseThrow(() -> new ResourceNotFoundException("floor with "+ desk.getFloor().getId()+" not found"));
		
		
		Integer totalDeskInAfloor = deskRepo.countByFloorId(desk.getFloor().getId());
		
		deskName = floor.getName();
		newDesk.setFloor(floor);
		newDesk.setState(DeskState.AVAILABLE);
		newDesk.setName(deskName);
		newDesk.setOrder(totalDeskInAfloor);
		
		return deskRepo.save(newDesk);
	}
	
	@Override
	public Desk getDeskById(Long id) {
		// TODO Auto-generated method stub
		Desk desk = new Desk();
		
		desk = deskRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("desk"+id+"not found"));
		
		return desk;
	}
	
	@Override
	public Desk disableDesk(Long id) {
		Desk desk = deskRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("desk"+id+"not found"));

		if(desk == null) {
			return null;
		}
		
		desk.setIsDisabled(1);
		
		
		return deskRepo.save(desk);
	}
	
	@Override
	public Desk updateDesk(Long id, Desk deskRequest) {
		Desk desk = deskRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("desk"+id+"not found"));
		
		if(desk == null) {
			return null;
		}
		
		desk.setSize(deskRequest.getSize()==0?desk.getSize():deskRequest.getSize());
		desk.setName(deskRequest.getName() == null?desk.getName():deskRequest.getName());
		desk.setState(deskRequest.getState() == null?desk.getState():deskRequest.getState());
		
		return deskRepo.save(desk);
		
	}
	
	@Override
	public Page<Desk> getDeksByRestaurantId(Long retaurantId, int pageNumber, int size) {
		validatePageNumberAndSize(pageNumber);
		Pageable pageable = PageRequest.of(pageNumber, size,Sort.Direction.DESC,"createdAt");
		
		return this.deskRepo.findByFloorRestaurantId(retaurantId, pageable);
	}

	 public String validatePageNumberAndSize(int pageNumber) {
	        if (pageNumber < 0) {
	            throw new BadRequestException("Page number cannot be less than one.");
	        }
	        return "";
	    }

	@Override
	public Collection<Desk> getDeksByRestaurantIdRaw(Long restaurantId) {
		// TODO Auto-generated method stub
		return this.deskRepo.findByFloorRestaurantIdAndIsDisabled(restaurantId,0);
	}
	

	@Override
	public Collection<Desk> getAllDesks() {
		// TODO Auto-generated method stub
		return deskRepo.findAll();
	}

	
	@Override
	public Desk deleteDesk(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
