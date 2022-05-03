package ee.annjakubel.people.controller;

import ee.annjakubel.people.cache.PeopleCache;
import ee.annjakubel.people.model.People;
import ee.annjakubel.people.model.PeopleResponse;
import ee.annjakubel.people.service.PeopleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Log4j2
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @Autowired
    PeopleCache cache;

    @GetMapping("people")
    public ResponseEntity<List<PeopleResponse>> getAllPeople() {
        return ResponseEntity.ok()
                .body(peopleService.getAllPeople());
    }

    @PostMapping("people")
    public ResponseEntity<PeopleResponse> addPeople() {
        log.info("Initializing function");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peopleService.addPerson());

    }

    @GetMapping("people/{id}")
    public ResponseEntity<PeopleResponse> getOnePerson(@PathVariable Long id) throws ExecutionException {
        return ResponseEntity.ok().body(cache.getPerson(id));
    }

    @DeleteMapping("people/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        return ResponseEntity.ok().body(peopleService.deletePerson(id));
    }

    @PutMapping("people/{id}")
    public ResponseEntity<PeopleResponse> editPerson(@PathVariable Long id) {
        return null;
    }
}
