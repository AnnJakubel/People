package ee.annjakubel.people.service;



import ee.annjakubel.people.model.People;
import ee.annjakubel.people.model.PeopleResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class PeopleService {

    @Value("${people.url}")
    String url;

    @Value("${key.for.url}")
    String urlKey;

    @Autowired
    RestTemplate restTemplate;

    public List<PeopleResponse> getAllPeople() {
        List allPeople = new ArrayList<>();
        ResponseEntity<PeopleResponse[]> response =
                restTemplate.exchange(url, HttpMethod.GET, null, PeopleResponse[].class);
        allPeople = Arrays.asList(response.getBody());
        return allPeople;
    }

    public PeopleResponse addPerson() {
        People person = new People();
        log.info("Creating new person");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<People> httpEntity = new HttpEntity<>(person, headers);
        log.info("Sending request");
        ResponseEntity<PeopleResponse> response =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, PeopleResponse.class);
        PeopleResponse addedPerson = response.getBody();
        return addedPerson;
    }

    public PeopleResponse getOne(Long id) {
        String fetchIdUrl = "https://my.api.mockaroo.com/kasutajad/" + id + urlKey;
        ResponseEntity <PeopleResponse> response =
                restTemplate.exchange(fetchIdUrl, HttpMethod.GET, null, PeopleResponse.class);
        return response.getBody();
    }

    public String deletePerson(Long id) {
        String fetchIdUrl = "https://my.api.mockaroo.com/kasutajad/" + id + urlKey;
        restTemplate.exchange(fetchIdUrl, HttpMethod.DELETE, null, PeopleResponse.class);
        return "Successfully deleted";
    }
}
