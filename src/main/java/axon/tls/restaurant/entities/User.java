package axon.tls.restaurant.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import axon.tls.restaurant.config.Constants;

@Entity
@JsonFilter(Constants.USER_FILTER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")

public class User extends AuditModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(phone, user.phone)
				&& Objects.equals(username, user.username) && Objects.equals(password, user.password)
				&& Objects.equals(email, user.email) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, phone, username, password, email,lastName,firstName);
	}

	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	@Size(min = 3, max = 50)
	private String username;

	@Column(name = "phone")
	@Size(max = 13)
	private String phone;

	@Column(name = "password", nullable = false)
	@Size(min = 6, max = 100)
	private String password;

	@Column(name = "email", nullable = false,unique = true)
	@Size(max = 50)
	@Email
	private String email;

	@Column(name = "first_name")
	@Size(max = 150)
	private String firstName;
	
	@Column(name = "last_name")
	@Size(max = 150)
	private String lastName;
	
	public String getAvatar() {
		return avatar;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name="avatar_url")
	private String avatar;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Restaurant> restaurants = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(Long id) {
		super();
		this.id = id;
	}

	public User(User user) {
		super();
		this.id = user.id;
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.phone = user.phone;
		this.lastName = user.lastName;
		this.firstName = user.firstName;
		this.avatar = user.avatar;
	}


	

	public User(Long id, @Size(min = 3, max = 50) String username, @Size(max = 13) String phone,
			@Size(min = 6, max = 100) String password, @Size(max = 50) @Email String email,
			@Size(max = 150) String firstName, @Size(max = 150) String lastName, String avatar,
			Set<Restaurant> restaurants, Set<RoleEntity> roles) {
		super();
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.avatar = avatar;
		this.restaurants = restaurants;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return lastName;
	}

	public void setName(String name) {
		this.lastName = name;
	}

}