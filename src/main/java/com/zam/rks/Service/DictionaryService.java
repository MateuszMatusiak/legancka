package com.zam.rks.Service;

import com.zam.rks.Repository.DictionaryRepository;
import com.zam.rks.model.Dictionary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope
public class DictionaryService {

	private final DictionaryRepository dictionaryRepository;

	public DictionaryService(DictionaryRepository dictionaryRepository) {
		this.dictionaryRepository = dictionaryRepository;
	}

	public List<Dictionary> getDictionary(){
		return dictionaryRepository.findAll();
	}
}
