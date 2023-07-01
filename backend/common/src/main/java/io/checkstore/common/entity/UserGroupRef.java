package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(value = CommonConstants.EntityName.GROUP_MEMBER)
public class UserGroupRef implements Serializable {
    private Long groupId;
}
