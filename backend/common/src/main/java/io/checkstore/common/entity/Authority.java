package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Setter
@Getter
@ToString
@Table(value = CommonConstants.EntityName.AUTHORITY)
public class Authority extends AbstractJdbcEntity<Long> {

    private Long featureId;
    private String name;
    private String description;
    private Integer status;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(featureId, authority.featureId) && Objects.equals(name, authority.name);
     }

     @Override
     public int hashCode(){
        return Objects.hash(featureId, name);
     }


}
