package ee.annjakubel.people.service;



import ee.annjakubel.people.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PeopleService {

    @Value("${people.url}")
    String url;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<List<People>> getAllPeople() {
        ResponseEntity<List<People>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, People[].class);

        return response;
    }

    public void addPerson() {
        People person = new People();
        restTemplate.exchange(url, HttpMethod.POST, null, People.class);
    }


}
