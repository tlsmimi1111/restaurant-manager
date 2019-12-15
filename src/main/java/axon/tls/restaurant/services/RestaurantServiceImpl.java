package axon.tls.restaurant.services;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.entities.User;
import axon.tls.restaurant.exception.ResourceNotFoundException;
import axon.tls.restaurant.repository.RestaurantRepository;
import axon.tls.restaurant.repository.UserRepository;
import axon.tls.restaurant.services.provider.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restRepo;
	
	@Autowired
	UserRepository userRepo;
	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		Restaurant newRest = new Restaurant(restaurant);
		
		
		List<User> users = userRepo.findAll();
		
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		newRest.setUser(user);
		
		return restRepo.save(newRest);
	}
	@Override
	public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
		Restaurant rest = restRepo.getOne(id);
		
		if(rest == null) {
			return null;
		}
		
		rest.setAddress(restaurant.getAddress()==null?rest.getAddress():restaurant.getAddress());
		rest.setName(restaurant.getName()==null?rest.getName():restaurant.getName());
		
		return restRepo.save(rest);
		
	}
	@Override
	public Restaurant disableRestaurant(Long id) {
		Restaurant rest = restRepo.getOne(id);

		if(rest == null) {
			return null;
		}
		
		rest.setIsDisabled(1);
		
		// set all floor to disable 
		
		return restRepo.save(rest);
	}
	@Override
	public Collection<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return restRepo.findAll();
	}
	@Override
	public Restaurant getRestaurantById(Long id) {
		// TODO Auto-generated method stub
		Restaurant rest = new Restaurant();
		
		rest = restRepo.findByIdAndIsDisabled(id, 0).orElseThrow(()-> new ResourceNotFoundException("restaurant"+id+"not found"));
		
		return rest;
	}
	@Override
	public Restaurant deleteRestaurant(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<Restaurant> getAllRestaurantsOfCurrentUser(Long userId) {
		List<Restaurant> restaurants = restRepo.findByUserIdAndIsDisabled(userId, 0).orElseThrow(
				()-> new ResourceNotFoundException("You do not have any restaurants yet")
				);
		
		return restaurants;
	}

	
	
}
