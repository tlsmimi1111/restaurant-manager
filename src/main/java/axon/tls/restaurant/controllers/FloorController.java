package axon.tls.restaurant.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.Floor;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.services.provider.FloorService;
import axon.tls.restaurant.services.provider.RestaurantService;
import axon.tls.restaurant.config.ApiConfig;

@CrossOrigin
@RestController
@RequestMapping(ApiConfig.PREFIX)
public class FloorController {
	
	   private Set<String> keptRestaurantFields = new HashSet<>();
	   
	   private Set<String> keptUserFields = new HashSet<>();

	    public FloorController() {
	        this.keptUserFields.add("name");
	        this.keptUserFields.add("email");
	        this.keptUserFields.add("phone");
	        this.keptUserFields.add("id");
	        
	        this.keptRestaurantFields.add("size");
	        this.keptRestaurantFields.add("name");
	        this.keptRestaurantFields.add("address");
	        this.keptRestaurantFields.add("id");

	    }
	
	@Autowired
	FloorService floorService;
	
	@PostMapping(value = ApiConfig.URI_FLOOR_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createFloor(@RequestBody @Valid Floor floorRequest) {
		MappingJacksonValue newFloor =  this.filterData(floorService.createFloor(floorRequest));
		return new ResponseEntity (newFloor,HttpStatus.CREATED);
	}
	
	@GetMapping(value =ApiConfig.URI_FLOOR_GET_ALL)
	public ResponseEntity getAllFloors() {
		MappingJacksonValue listFloors = this.filterData(floorService.getAllFloors());
		
		return new ResponseEntity (listFloors,HttpStatus.OK);
	}
	
	@GetMapping(value = ApiConfig.URI_FLOOR_GET_ONE)
	public ResponseEntity getFloorById(@PathVariable(value= "floorId") Long floorId) {
		MappingJacksonValue floor = this.filterData(floorService.getFloorById(floorId));
		return new ResponseEntity (floor,HttpStatus.OK);
		
	}
	
	@PatchMapping(value = ApiConfig.URI_FLOOR_DISABLE_ONE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity disableFloorById(@RequestBody @Valid Floor floorRequest) {
		Floor floor = floorService.disableFloor(floorRequest.getId());
		MappingJacksonValue disabledFloor = this.filterData(floor);
		if(floor != null) {
		return new ResponseEntity(disabledFloor,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Floor is not disabled successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PatchMapping(value = ApiConfig.URI_FLOOR_UPDATE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateFloor(@PathVariable(value = "floorId") Long id,@RequestBody @Valid Floor floorRequest) {
		Floor floor = floorService.updateFloor(id, floorRequest);
		
		if(floor != null) {
			MappingJacksonValue response = this.filterData(floor);
			return new ResponseEntity(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Floor is not updated successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping(value = ApiConfig.URI_FLOOR_GET_BY_RESTAURANT_ID)
	public ResponseEntity getFloorsByRestaurantId(@PathVariable(value="restaurantId") Long restaurantId) {
		MappingJacksonValue floors = this.filterData(this.floorService.getFloorByRestaurantId(restaurantId));
		
		return new ResponseEntity(floors,HttpStatus.OK);
		
	}
	
	private MappingJacksonValue filterData(Object FloorService) {
        MappingJacksonValue wrapper = new MappingJacksonValue(FloorService);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(Constants.FLOOR_FILTER, SimpleBeanPropertyFilter.serializeAll())
                .addFilter(Constants.RESTAURANT_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept(this.keptRestaurantFields))
                .addFilter(Constants.DESK_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id"));
        wrapper.setFilters(filterProvider);
        return wrapper;
    }
	
}
