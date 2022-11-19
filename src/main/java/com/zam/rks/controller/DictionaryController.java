package com.zam.rks.controller;

import com.zam.rks.Service.DictionaryService;
import com.zam.rks.model.Dictionary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryController {

	private final DictionaryService dictionaryService;

	public DictionaryController(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@GetMapping("/dictionary")
	public List<Dictionary> getDictionary() {
		return dictionaryService.getDictionary();
	}

	@GetMapping("/dictionary/{entry}")
	public List<Dictionary> getDictionaryByEntry(@PathVariable String entry) {
		return dictionaryService.getDictionaryByEntry(entry);
	}

	@PutMapping("/dicitionary")
	public Dictionary insertEntry(@RequestBody Dictionary dictionary) {
		return dictionaryService.insertEntry(dictionary);
	}
}
