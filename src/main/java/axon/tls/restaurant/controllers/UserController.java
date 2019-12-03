package axon.tls.restaurant.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.User;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.repository.UserRepository;
import axon.tls.restaurant.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(ApiConfig.PREFIX)
public class UserController {
	
	@Autowired 
	UserService userService;

	@PostMapping(value = ApiConfig.URI_USERS_CREATE)
	public ResponseEntity<User> createUser(@RequestBody @Valid User userRequest){
		
		MappingJacksonValue user = this.filterData(userService.createUser(userRequest));
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	private MappingJacksonValue filterData(Object userService) {
        MappingJacksonValue wrapper = new MappingJacksonValue(userService);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(Constants.USER_FILTER, SimpleBeanPropertyFilter.serializeAll())
                .addFilter(Constants.RESTAURANT_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id","name"));
        wrapper.setFilters(filterProvider);
        return wrapper;
    }
	
}
