package ee.annjakubel.people.model;

import lombok.Data;

@Data
public class People {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String ip_address;

}
