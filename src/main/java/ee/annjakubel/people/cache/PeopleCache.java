package ee.annjakubel.people.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class PeopleCache {

    @Autowired
    RestTemplate restTemplate;
}
