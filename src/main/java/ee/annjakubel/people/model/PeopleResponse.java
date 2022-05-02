package ee.annjakubel.people.model;

import lombok.Data;

@Data
public class PeopleResponse {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String ip_address;
}
