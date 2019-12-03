package axon.tls.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import axon.tls.restaurant.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  	User findByUsername(String userName);
}
