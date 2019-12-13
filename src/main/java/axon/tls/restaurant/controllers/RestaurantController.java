package axon.tls.restaurant.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.models.CustomUserDetails;
import axon.tls.restaurant.services.provider.CurrentUser;
import axon.tls.restaurant.services.provider.RestaurantService;
import axon.tls.constant.RoleConstants;
import axon.tls.restaurant.config.ApiConfig;

@CrossOrigin
@RestController
@RequestMapping(ApiConfig.PREFIX)
public class RestaurantController {
	
	   private Set<String> keepedFields = new HashSet<>();

	    public RestaurantController() {
	        this.keepedFields.add("name");
	        this.keepedFields.add("email");
	        this.keepedFields.add("phone");
	        this.keepedFields.add("username");
	        this.keepedFields.add("password");
	    }
	
	@Autowired
	RestaurantService restService;
	
	@PostMapping(value = ApiConfig.URI_RESTAURANT_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createRestaurant(@RequestBody @Valid Restaurant restaurant) {
		restService.createRestaurant(restaurant);
		return new ResponseEntity ("Restaurant is created successfully",HttpStatus.CREATED);
	}
	
	@GetMapping(value =ApiConfig.URI_RESTAURANT_GET_ALL)
	public ResponseEntity getAllRestaurants(@CurrentUser CustomUserDetails currentUser ) {
	
		
		MappingJacksonValue listRestaurants = this.filterData
				(restService.getAllRestaurantsOfCurrentUser(currentUser.getUser().getId()));
		
		return new ResponseEntity (listRestaurants,HttpStatus.OK);
	}
	
	@GetMapping(value = ApiConfig.URI_RESTAURANT_GET_ONE)
	public ResponseEntity getRestaurantById(@PathVariable(value= "restaurantId") Long restaurantId) {
		MappingJacksonValue rest = this.filterData(restService.getRestaurantById(restaurantId));
		return new ResponseEntity (rest,HttpStatus.OK);
		
	}
	
	@PutMapping(value = ApiConfig.URI_RESTAURANT_DISABLE_ONE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity disableRestaurantById(@RequestBody @Valid Restaurant restaurant) {
		Restaurant rest = restService.disableRestaurant(restaurant.getId());
		if(rest != null) {
		return new ResponseEntity("Restaurant is disabled successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Restaurant is not disabled successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PutMapping(value = ApiConfig.URI_RESTAURANT_UPDATE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateRestaurant(@PathVariable(value = "restaurantId") Long id,@RequestBody @Valid Restaurant rest) {
		Restaurant restaurant = restService.updateRestaurant(id, rest);
		
		if(restaurant != null) {
			MappingJacksonValue response = this.filterData(restaurant);
			return new ResponseEntity(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Restaurant is not updated successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	private MappingJacksonValue filterData(Object RestaurantService) {
        MappingJacksonValue wrapper = new MappingJacksonValue(RestaurantService);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(Constants.RESTAURANT_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("user"))
                .addFilter(Constants.FLOOR_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id"));
        wrapper.setFilters(filterProvider);
        return wrapper;
    }
	
}
