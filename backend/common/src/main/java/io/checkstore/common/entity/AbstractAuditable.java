package io.checkstore.common.entity;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractAuditable<U extends Serializable> implements Serializable {
    @CreatedBy
    protected @Nullable U createdBy;

    @CreatedDate
    protected @Nullable LocalDateTime createdDate;

    @LastModifiedBy
    protected @Nullable U lastModifiedBy;

    @LastModifiedDate
    protected @Nullable LocalDateTime lastModifiedDate;
}
