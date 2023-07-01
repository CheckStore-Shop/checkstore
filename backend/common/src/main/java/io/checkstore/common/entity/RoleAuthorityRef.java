package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = CommonConstants.EntityName.ROLE_AUTHORITY)
public class RoleAuthorityRef implements Serializable {
    private Long authorityId;
}
