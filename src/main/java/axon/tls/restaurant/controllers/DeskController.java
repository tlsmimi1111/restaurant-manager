package axon.tls.restaurant.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.Desk;
import axon.tls.restaurant.entities.Floor;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.services.provider.DeskService;
import axon.tls.restaurant.services.provider.FloorService;
import axon.tls.restaurant.services.provider.RestaurantService;
import axon.tls.restaurant.config.ApiConfig;

@CrossOrigin
@RestController
@RequestMapping(ApiConfig.PREFIX)
public class DeskController {

	private Set<String> keptRestaurantFields = new HashSet<>();

	private Set<String> keptFloorFields = new HashSet<>();

	private Set<String> keptUserFields = new HashSet<>();

	public DeskController() {
		this.keptUserFields.add("name");
		this.keptUserFields.add("email");
		this.keptUserFields.add("phone");
		this.keptUserFields.add("id");

		this.keptRestaurantFields.add("size");
		this.keptRestaurantFields.add("name");
		this.keptRestaurantFields.add("address");
		this.keptRestaurantFields.add("id");

		this.keptFloorFields.add("size");
		this.keptFloorFields.add("name");
		this.keptFloorFields.add("id");
	}

	@Autowired
	DeskService deskService;

	@PostMapping(value = ApiConfig.URI_DESK_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDesk(@RequestBody @Valid Desk deskRequest) {
		MappingJacksonValue newDesk =  this.filterData(deskService.createDesk(deskRequest));
		return new ResponseEntity(newDesk, HttpStatus.CREATED);
	}

	@GetMapping(value = ApiConfig.URI_DESK_GET_ALL)
	public ResponseEntity getAllDesks() {
		MappingJacksonValue listDesks = this.filterData(deskService.getAllDesks());

		return new ResponseEntity(listDesks, HttpStatus.OK);
	}

	@GetMapping(value = ApiConfig.URI_DESK_GET_ONE)
	public ResponseEntity getDeskById(@PathVariable(value = "deskId") Long deskId) {
		MappingJacksonValue desk = this.filterData(deskService.getDeskById(deskId));
		return new ResponseEntity(desk, HttpStatus.OK);

	}

	@PatchMapping(value = ApiConfig.URI_DESK_DISABLE_ONE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity disableDeskById(@RequestBody @Valid Desk deskRequest) {
		Desk desk = deskService.disableDesk(deskRequest.getId());
		if (desk != null) {
			return new ResponseEntity(desk, HttpStatus.OK);
		} else {
			return new ResponseEntity("Desk is not disabled successfully", HttpStatus.NOT_MODIFIED);
		}
	}

	@PatchMapping(value = ApiConfig.URI_DESK_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateDesk(@PathVariable(value = "deskId") Long id, @RequestBody Desk deskRequest) {
		Desk desk = deskService.updateDesk(id, deskRequest);

		if (desk != null) {
			MappingJacksonValue response = this.filterData(desk);
			return new ResponseEntity(response, HttpStatus.OK);
		} else {
			return new ResponseEntity("Desk is not updated successfully", HttpStatus.NOT_MODIFIED);
		}
	}

	@GetMapping(value = ApiConfig.URI_GET_DESK_BY_RESTAURANT_ID)
	public ResponseEntity getDesksByRestaurantId(@RequestParam(value = "restaurantId") Long restaurantId,
			@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		Page<Desk> desks = deskService.getDeksByRestaurantId(restaurantId, page, size);
		MappingJacksonValue listDesks = this.filterData(deskService.getDeksByRestaurantId(restaurantId, page, size));
		return new ResponseEntity(listDesks,HttpStatus.OK);
	}
	
	@GetMapping(value = "/desks/restaurantId")
	public ResponseEntity getDesksByRestaurantId(@RequestParam(value = "restaurantId") Long restaurantId) {
		MappingJacksonValue listDesks = this.filterData(deskService.getDeksByRestaurantIdRaw(restaurantId));
		return new ResponseEntity(listDesks,HttpStatus.OK);
	}

	private MappingJacksonValue filterData(Object DeskService) {
		MappingJacksonValue wrapper = new MappingJacksonValue(DeskService);
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter(Constants.DESK_FILTER, SimpleBeanPropertyFilter.serializeAll())
				.addFilter(Constants.BILL_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id"))
				.addFilter(Constants.FLOOR_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept(this.keptFloorFields));
		wrapper.setFilters(filterProvider);
		return wrapper;
	}

}
