package com.zam.rks.Service;

import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.LoginCredentials;
import com.zam.rks.model.User;
import com.zam.rks.security.PasswordEncoder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

	@Transactional
	public ResponseEntity<String> saveNewUser(LoginCredentials user) {
		Optional<User> test = userRepository.findByEmail(user.getEmail());
		if (test.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
		}
		User userToSave = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()));
		userRepository.save(userToSave);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully registered");

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
