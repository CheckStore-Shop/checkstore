package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Table(value = CommonConstants.EntityName.GROUP_MEMBER)
public class GroupUserRef implements Serializable {
    private Long userId;
}
