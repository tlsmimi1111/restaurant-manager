package axon.tls.restaurant.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import axon.tls.restaurant.config.Constants;


@Entity
@Table(name = "restaurants")
@JsonFilter(Constants.RESTAURANT_FILTER)
public class Restaurant extends AuditModel<String> {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address")
	private String address;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class,optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<Floor> floors = new HashSet<>();

	public Restaurant() {
		super();
	}

	public Restaurant(Restaurant rest) {
		super();
		this.name = rest.name;
		this.address = rest.address;
	}
	
	public Set<Floor> getFloors() {
		return floors;
	}



	public void setFloors(Set<Floor> floors) {
		this.floors = floors;
	}


	public Restaurant(Long id, String name) {
		super();
		this.name = name;
	}

	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
