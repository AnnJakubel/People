package ee.annjakubel.people.service;



import ee.annjakubel.people.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeopleService {

    @Value("${people.url}")
    String url;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<People[]> getAllPeople() {
        ResponseEntity<People[]> response =
                restTemplate.exchange(url, HttpMethod.GET, null, People[].class);

        return response;
    }


}
