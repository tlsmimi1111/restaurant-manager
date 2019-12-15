package axon.tls.restaurant.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.User;
import axon.tls.restaurant.models.CustomUserDetails;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;

import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.repository.UserRepository;
import axon.tls.restaurant.services.UserService;
import axon.tls.restaurant.services.provider.CurrentUser;

@RestController
@CrossOrigin
@RequestMapping(ApiConfig.PREFIX)
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = ApiConfig.URI_USERS_CREATE)
	public ResponseEntity<User> createUser(@RequestBody @Valid User userRequest) {

		MappingJacksonValue user = this.filterData(userService.createUser(userRequest));
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/auth/currentUser")
	public ResponseEntity getCurrentUser(@CurrentUser CustomUserDetails currentUserDetails){
		User currentUser = new User(currentUserDetails.getUser());
		MappingJacksonValue user = this.filterData(currentUser);
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	@PatchMapping(value = ApiConfig.URI_USERS_UPDATE)
	public ResponseEntity updateCurrentUser(@CurrentUser CustomUserDetails currentUserDetails, @RequestBody @Valid User updateUser) {
		User currentUser = currentUserDetails.getUser();
		MappingJacksonValue updatedUser = this.filterData(userService.updateUser(currentUser.getId(), updateUser));
		return new ResponseEntity (updatedUser,HttpStatus.OK);
	}

	private MappingJacksonValue filterData(Object userService) {
		MappingJacksonValue wrapper = new MappingJacksonValue(userService);
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter(Constants.USER_FILTER, SimpleBeanPropertyFilter.serializeAll())
				.addFilter(Constants.RESTAURANT_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
		wrapper.setFilters(filterProvider);
		return wrapper;
	}

}
