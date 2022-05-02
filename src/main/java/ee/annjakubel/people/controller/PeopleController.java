package ee.annjakubel.people.controller;

import ee.annjakubel.people.model.People;
import ee.annjakubel.people.model.PeopleResponse;
import ee.annjakubel.people.service.PeopleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping("people")
    public ResponseEntity<List<PeopleResponse>> getAllPeople() {
        return ResponseEntity.ok()
                .body(peopleService.getAllPeople());
    }

    @PostMapping("people")
    public ResponseEntity<PeopleResponse> addPeople() {
        log.info("Initializing function");
        peopleService.addPerson();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peopleService.addPerson());

    }

    @GetMapping("people/{id}")
    public ResponseEntity<PeopleResponse> getOnePerson(@PathVariable int id) {
        peopleService.getOne(id);
        return ResponseEntity.ok().body(peopleService.getOne(id));
    }
}
