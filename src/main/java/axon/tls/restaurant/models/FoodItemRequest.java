package axon.tls.restaurant.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import axon.tls.restaurant.entities.FoodItem;
import axon.tls.restaurant.entities.Restaurant;
import axon.tls.restaurant.entities.RowItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class FoodItemRequest {
	
	private Long id;
	

	private String image_url;
	
	
	private String name;

	private Restaurant restaurant;
	
	private int price; 
	

	private MultipartFile image;
}
