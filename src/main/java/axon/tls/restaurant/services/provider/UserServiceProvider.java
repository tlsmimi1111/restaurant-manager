package axon.tls.restaurant.services.provider;

import axon.tls.restaurant.entities.User;

public interface UserServiceProvider {
	public abstract User updateUser(Long id,User user);
}
