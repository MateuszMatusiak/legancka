package com.zam.rks.Service;

import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.User;
import com.zam.rks.model.UserRole;
import com.zam.rks.security.PasswordEncoder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public ResponseEntity<String> saveNewUser(User user) {
		Optional<User> test = userRepository.findByUsername(user.getUsername());
		if (test.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
		}
		User userToSave = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail(), user.getPhoneNumber(), UserRole.ROLE_USER);
		userRepository.save(userToSave);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully registered");

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
