package ee.annjakubel.people.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.annjakubel.people.model.People;
import ee.annjakubel.people.model.PeopleResponse;
import ee.annjakubel.people.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class PeopleCache {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PeopleService peopleService;


    private LoadingCache<Long, PeopleResponse> peopleLoadingCache = CacheBuilder
            .newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, PeopleResponse>() {
                @Override
                public PeopleResponse load(Long id) throws Exception {
                    return peopleService.getOne(id);
                }
            });

    public PeopleResponse getPerson(Long id) throws ExecutionException {
        return peopleLoadingCache.get(id);
    }

    public void emptyCache() {
        peopleLoadingCache.invalidateAll();
    }

    public void updateCache(PeopleResponse person) {
        peopleLoadingCache.put(person.getId(), person);
    }
}
