package axon.tls.restaurant.models;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import axon.tls.restaurant.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
	User user;
//	private static final long serialVersionUID = 1L;
	
//	private Long id;
	
//	@JsonIgnoreProperties
//	private String password;
//	private String username;
//	private String name;
//	private String email;
//	private String phone;
//	private Collection<? extends GrantedAuthority> authorities;

	// function use to verify roles of a user
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// for test purpose only return one role "user" only"
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	
	  @Override
	    public String getPassword() {
	        return user.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return user.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
//
//	public CustomUserDetails(User user) {
//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
//
//		this.password = user.getPassword();
//		this.username = user.getUsername();
//		this.phone = user.getPhone();
//		this.name = user.getName();
//		this.email = user.getEmail();
//		this.authorities = authorities;
//		this.id  = user.getId();
//	}

}
