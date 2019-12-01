package axon.tls.restaurant.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import axon.tls.restaurant.entities.User;
import axon.tls.restaurant.models.CustomUserDetails;
import axon.tls.restaurant.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	  @Autowired
	  PasswordEncoder passwordEncoder;

	    @Override
	    public UserDetails loadUserByUsername(String username) {
	        // Kiểm tra xem user có tồn tại trong database không?
	        User user = userRepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new CustomUserDetails(user);
	    }

	    // JWTAuthenticationFilter sẽ sử dụng hàm này
	    @Transactional
	    public UserDetails loadUserById(Long id) {
	        User user = userRepository.findById(id).orElseThrow(
	                () -> new UsernameNotFoundException("User not found with id : " + id)
	        );

	        return new CustomUserDetails(user);
	    }
	    
	    
	    public User createUser(User user) {
	    	User newUser = new User(user);
	    	
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	return userRepository.save(user);
	    }
	    

}
