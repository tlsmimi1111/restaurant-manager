package axon.tls.restaurant.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import axon.tls.restaurant.entities.Floor;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.entities.User;
import axon.tls.restaurant.exception.ResourceNotFoundException;
import axon.tls.restaurant.repository.FloorRepository;
import axon.tls.restaurant.repository.RestaurantRepository;
import axon.tls.restaurant.repository.UserRepository;
import axon.tls.restaurant.services.provider.FloorService;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	FloorRepository floorRepo;

	@Autowired
	RestaurantRepository restaurantRepo;

	@Override
	public Floor createFloor(Floor floor) {
		Floor newFloor = new Floor(floor);

		Restaurant rest = restaurantRepo.findByIdAndIsDisabled(floor.getRestaurant().getId(), 0)
				.orElseThrow(() -> new ResourceNotFoundException("restaurant with "+ floor.getRestaurant().getId()+" not found"));
		
		newFloor.setRestaurant(rest);

		return floorRepo.save(newFloor);
	}

	@Override
	public Floor updateFloor(Long id, Floor floorRequest) {
		Floor floor = floorRepo.getOne(id);

		if (floor == null) {
			return null;
		}

		if(floorRequest.getName() != null) {
			floor.setName(floorRequest.getName());
		}
		
		if(floorRequest.getSize() != 0) {
			floor.setSize(floorRequest.getSize());
		}

		return floorRepo.save(floor);

	}

	@Override
	public Collection<Floor> getFloorByRestaurantId(Long restaurantId) {
		List<Floor> floors = floorRepo.findAllByRestaurantIdAndIsDisabled(restaurantId,0);
		
		return floors;
	}

	@Override
	public Floor disableFloor(Long id) {
		Floor floor = floorRepo.getOne(id);

		if (floor == null) {
			return null;
		}

		floor.setIsDisabled(1);

		// set all desk to disable

		return floorRepo.save(floor);
	}

	@Override
	public Collection<Floor> getAllFloors() {
		// TODO Auto-generated method stub
		return floorRepo.findAll();
	}

	@Override
	public Floor getFloorById(Long id) {
		// TODO Auto-generated method stub
		Floor floor = new Floor();

		floor = floorRepo.findByIdAndIsDisabled(id, 0)
				.orElseThrow(() -> new ResourceNotFoundException("floor with " + id + "not found"));

		return floor;
	}

	@Override
	public Floor deleteFloor(Long id) {
		// TODO Auto-generated method stub
		floorRepo.deleteById(id);
		
		return null;
	}

}
