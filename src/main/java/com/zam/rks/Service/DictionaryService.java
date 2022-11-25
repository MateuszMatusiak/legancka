package com.zam.rks.Service;

import com.zam.rks.Repository.DictionaryRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Dictionary;
import com.zam.rks.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Scope
public class DictionaryService {

	private final DictionaryRepository dictionaryRepository;
	private final UserRepository userRepository;

	public List<Dictionary> getDictionary() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();
		return dictionaryRepository.findAllByGroup(user.getSelectedGroup());
	}

	@Transactional
	public Dictionary insertEntry(Dictionary dictionary) {
		Optional<Dictionary> test = dictionaryRepository.findByEntry(dictionary.getEntry());
		if (test.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Entry exists");
		}
		Dictionary dictionaryToSave = new Dictionary(dictionary.getEntry(), dictionary.getDescription());
		return dictionaryRepository.save(dictionaryToSave);
	}
}
