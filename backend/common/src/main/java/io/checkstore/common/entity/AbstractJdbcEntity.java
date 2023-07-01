package io.checkstore.common.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
public abstract class AbstractJdbcEntity<ID extends Serializable> extends AbstractAuditable<Long> {
    @Id
    protected ID id;



}
