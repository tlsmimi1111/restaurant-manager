package axon.tls.restaurant.controllers;

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

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.RowItem;
import axon.tls.restaurant.services.provider.RowItemService;

@CrossOrigin
@RestController
@RequestMapping(ApiConfig.PREFIX)
public class RowItemController {

	@Autowired 
	RowItemService rowItemService;
	
	@PostMapping(value = ApiConfig.URI_ROW_ITEM_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createRowItem(@RequestBody @Valid RowItem rowItemRequest) {
		MappingJacksonValue newRowItem =  this.filterData(rowItemService.createRowItem(rowItemRequest));
		return new ResponseEntity (newRowItem,HttpStatus.CREATED);
	}

	
	@GetMapping(value = ApiConfig.URI_ROW_ITEM_GET_ONE)
	public ResponseEntity getRowItemById(@PathVariable(value= "rowItemId") Long rowItemId) {
		MappingJacksonValue rowItem = this.filterData(rowItemService.getRowItemById(rowItemId));
		return new ResponseEntity (rowItem,HttpStatus.OK);
		
	}
	
	@PatchMapping(value = ApiConfig.URI_ROW_ITEM_DISABLE_ONE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity disableRowItemById(@RequestBody @Valid RowItem rowItemRequest) {
		RowItem rowItem = rowItemService.disableRowItem(rowItemRequest.getId());
		MappingJacksonValue disabledRowItem = this.filterData(rowItem);
		if(rowItem != null) {
		return new ResponseEntity(disabledRowItem,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("row item is not disabled successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PatchMapping(value = ApiConfig.URI_ROW_ITEM_UPDATE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateRowItem(@PathVariable(value = "rowItemId") Long id,@RequestBody @Valid RowItem rowItemRequest) {
		RowItem rowItem = rowItemService.updateRowItem(id, rowItemRequest);
		
		if(rowItem != null) {
			MappingJacksonValue response = this.filterData(rowItem);
			return new ResponseEntity(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("RowItem is not updated successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping(value = "/rowItems/billId")
	public ResponseEntity getRowItemsByBillId(@RequestParam(value = "billId") Long billId) {
		MappingJacksonValue listRowItems = this.filterData(rowItemService.getRowItemByBillId(billId));
		return new ResponseEntity(listRowItems,HttpStatus.OK);
	}
	
	private MappingJacksonValue filterData(Object rowItemService) {
        MappingJacksonValue wrapper = new MappingJacksonValue(rowItemService);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(Constants.ROW_ITEM_FILTER, SimpleBeanPropertyFilter.serializeAll())
                .addFilter(Constants.BILL_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id"))
                .addFilter(Constants.FOOD_ITEM_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price"));
        wrapper.setFilters(filterProvider);
        return wrapper;
    }
}
