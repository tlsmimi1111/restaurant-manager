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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.ec2.model.Storage;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sun.istack.Nullable;

import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.FoodItem;
import axon.tls.restaurant.models.FoodItemRequest;
import axon.tls.restaurant.services.StorageService;
import axon.tls.restaurant.services.provider.FoodItemService;

@CrossOrigin
@RestController
@RequestMapping(ApiConfig.PREFIX)
public class FoodItemController {
	private Set<String> keptRestaurantFields = new HashSet<>();
	
	public FoodItemController() {
		this.keptRestaurantFields.add("name");
		this.keptRestaurantFields.add("id");
	}
	
	@Autowired 
	FoodItemService itemService;
	
	@Autowired
	StorageService storageService;
	
	@PostMapping(value = ApiConfig.URI_ITEM_CREATE)
	public ResponseEntity createFoodItem(@RequestParam(value="file",required=false) MultipartFile file,@RequestParam("restaurantId") String restaurantId,@RequestParam("price") String price,@RequestParam("name") String name) {
		FoodItem item = new FoodItem();
		item.setPrice(Integer.parseInt(price));
		item.setName(name);
		
		if(file != null) {
			String imgUrl = storageService.saveFile(file);
			item.setImage_url(imgUrl);
		}
		
		MappingJacksonValue newFoodItem =  this.filterData(itemService.createFoodItem(item,Long.parseLong(restaurantId)));
		return new ResponseEntity(newFoodItem, HttpStatus.CREATED);
	}

	@PatchMapping(value = ApiConfig.URI_ITEM_UPDATE)
	public ResponseEntity updateFoodItem(@PathVariable(value = "itemId") Long id, @RequestParam(value="file",required=false) MultipartFile file,@RequestParam("price") String price,@RequestParam("name") String name) {
		FoodItem item = new FoodItem();
		item.setPrice(Integer.parseInt(price));
		item.setName(name);
		
		if(file != null) {
			String imgUrl = storageService.saveFile(file);
			item.setImage_url(imgUrl);
		}
		
		FoodItem foodItem = itemService.updateFoodItem(id, item);

		if (foodItem != null) {
			MappingJacksonValue response = this.filterData(foodItem);
			return new ResponseEntity(response, HttpStatus.OK);
		} else {
			return new ResponseEntity("FoodItem is not updated successfully", HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping(value = ApiConfig.URI_ITEM_GET_ONE)
	public ResponseEntity getFoodItemById(@PathVariable(value = "itemId") Long foodItemId) {
		MappingJacksonValue foodItem = this.filterData(itemService.getFoodItemById(foodItemId));
		return new ResponseEntity(foodItem, HttpStatus.OK);

	}

	@PatchMapping(value = ApiConfig.URI_ITEM_DISABLE_ONE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity disableFoodItemById(@RequestBody @Valid FoodItem foodItemRequest) {
		FoodItem foodItem = itemService.disableFoodItem(foodItemRequest.getId());
		if (foodItem != null) {
			return new ResponseEntity(this.filterData(foodItem), HttpStatus.OK);
		} else {
			return new ResponseEntity("food item is not disabled successfully", HttpStatus.NOT_MODIFIED);
		}
	}


	
	@GetMapping(value = "/foodItems/restaurantId")
	public ResponseEntity getFoodItemsByRestaurantId(@RequestParam(value = "restaurantId") Long restaurantId) {
		MappingJacksonValue listFoodItems = this.filterData(itemService.getFoodItemByRestaurantId(restaurantId));
		return new ResponseEntity(listFoodItems,HttpStatus.OK);
	}
	
	private MappingJacksonValue filterData(Object itemService) {
		MappingJacksonValue wrapper = new MappingJacksonValue(itemService);
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter(Constants.FOOD_ITEM_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("rowItems"))
				.addFilter(Constants.RESTAURANT_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept(this.keptRestaurantFields));
		wrapper.setFilters(filterProvider);
		return wrapper;
	}
}
