package ee.annjakubel.people.controller;

import ee.annjakubel.people.model.People;
import ee.annjakubel.people.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping("people")
    public ResponseEntity<People[]> getAllPeople() {
        return peopleService.getAllPeople();
    }

    @PostMapping
    public void addPeople(@RequestBody People person) {

    }
}
