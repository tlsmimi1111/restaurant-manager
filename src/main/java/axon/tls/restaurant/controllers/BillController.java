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
import com.google.j2objc.annotations.AutoreleasePool;

import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.config.Constants;
import axon.tls.restaurant.entities.Bill;
import axon.tls.restaurant.entities.Desk;
import axon.tls.restaurant.entities.DeskState;
import axon.tls.restaurant.entities.RowItem;
import axon.tls.restaurant.services.provider.BillService;
import axon.tls.restaurant.services.provider.DeskService;

@RestController
@CrossOrigin
@RequestMapping(ApiConfig.PREFIX)
public class BillController {

	@Autowired
	BillService billService;
	
	@Autowired
	DeskService deskService;
	
	@PostMapping(value = ApiConfig.URI_BILL_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createBill(@RequestBody @Valid Bill billRequest) {
		MappingJacksonValue newBill =  this.filterData(billService.createBill(billRequest));
		return new ResponseEntity (newBill,HttpStatus.CREATED);
	}

	
	@GetMapping(value = ApiConfig.URI_BILL_GET_ONE)
	public ResponseEntity getBillById(@PathVariable(value= "billId") Long billId) {
		MappingJacksonValue bill = this.filterData(billService.getBillById(billId));
		return new ResponseEntity (bill,HttpStatus.OK);
		
	}
	
	@PatchMapping(value = ApiConfig.URI_BILL_DISABLE_ONE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity disableBillById(@RequestBody @Valid Bill billRequest) {
		Bill bill = billService.disableBill(billRequest.getId());
		MappingJacksonValue disabledBill = this.filterData(bill);
		if(bill != null) {
		return new ResponseEntity(disabledBill,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("bill is not disabled successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PatchMapping(value = ApiConfig.URI_BILL_UPDATE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateBill(@PathVariable(value = "billId") Long id,@RequestBody @Valid Bill billRequest) {
		Bill bill = billService.updateBill(id, billRequest);
		
		if(bill != null) {
			MappingJacksonValue response = this.filterData(bill);
			return new ResponseEntity(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Bill is not updated successfully",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping(value = ApiConfig.URI_GET_BILL_BY_DESK_ID)
	public ResponseEntity getBillsByBillId(@RequestParam(value = "deskId") Long deskId) {
		MappingJacksonValue bill = this.filterData(billService.getBillByDeskId(deskId));
		return new ResponseEntity(bill,HttpStatus.OK);
	}
	
	private MappingJacksonValue filterData(Object billService) {
        MappingJacksonValue wrapper = new MappingJacksonValue(billService);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(Constants.BILL_FILTER, SimpleBeanPropertyFilter.serializeAllExcept("desk"))
                .addFilter(Constants.ROW_ITEM_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id","item","quantity"))
                .addFilter(Constants.FOOD_ITEM_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price"));
        wrapper.setFilters(filterProvider);
        return wrapper;
    }
}
