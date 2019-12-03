package axon.tls.restaurant.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;

import axon.tls.restaurant.config.Constants;

@Entity
@Table(name = "floor")
@JsonFilter(Constants.FLOOR_FILTER)
public class Floor extends AuditModel<String> {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int size;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "floor")
	private Set<Desk> desks = new HashSet<>();

	public Floor() {
		super();
	}

	public Floor(Floor floor) {
		// TODO Auto-generated constructor stub
		this.name = floor.getName();
		this.size = floor.getSize();
	}

	public Set<Desk> getDesks() {
		return desks;
	}

	public void setDesks(Set<Desk> desks) {
		this.desks = desks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
