package br.com.alura.api.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//use this annotation for application to understand this is the controller
@RestController
public class LanguageController {
    @Autowired
    private LanguageRepository repository;

    @GetMapping(value = "/languages")
    public List<Language> getLanguages() {
        List<Language> languages = repository.findAll();
        return languages;
    }

    @PostMapping(value = "/languages")
    public Language setLanguages(@RequestBody Language language) {
        Language saveLanguage = repository.save(language);
        return saveLanguage;
    }
}