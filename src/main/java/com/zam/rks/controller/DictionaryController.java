package com.zam.rks.controller;

import com.zam.rks.Service.DictionaryService;
import com.zam.rks.model.Dictionary;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

	private final DictionaryService dictionaryService;


	@GetMapping
	public List<Dictionary> getDictionary() {
		return dictionaryService.getDictionary();
	}

	@PutMapping
	public Dictionary insertEntry(@RequestBody Dictionary dictionary) {
		return dictionaryService.insertEntry(dictionary);
	}
}
