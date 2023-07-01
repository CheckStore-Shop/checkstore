package io.checkstore.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address extends AbstractJdbcEntity<String> {

    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String landMark;

}
