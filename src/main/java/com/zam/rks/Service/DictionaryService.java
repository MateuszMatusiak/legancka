package com.zam.rks.Service;

import com.zam.rks.Repository.DictionaryRepository;
import com.zam.rks.model.Dictionary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope
public class DictionaryService {

	private final DictionaryRepository dictionaryRepository;

	public DictionaryService(DictionaryRepository dictionaryRepository) {
		this.dictionaryRepository = dictionaryRepository;
	}

	public List<Dictionary> getDictionary() {
		return dictionaryRepository.findAll();
	}

	public List<Dictionary> getDictionaryByEntry(String entry) {
		return dictionaryRepository.findByEntryLike(entry);
	}

	public Dictionary insertEntry(Dictionary dictionary) {
		Optional<Dictionary> test = dictionaryRepository.findByEntry(dictionary.getEntry());
		if (test.isPresent()) {
			return null;
		}
		Dictionary dictionaryToSave = new Dictionary(dictionary.getEntry(), dictionary.getDescription());
		return dictionaryRepository.save(dictionaryToSave);
	}
}
