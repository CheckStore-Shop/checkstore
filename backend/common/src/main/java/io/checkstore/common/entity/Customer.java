package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

@Table(value = CommonConstants.EntityName.CUSTOMER)
public class Customer extends AbstractJdbcEntity<Long> {

    private String connectedAccountId;
    private String name;
    private String customerSubType;
    private Long userId;
    private Long AuxiliaryUserId;
    private Integer onBoarded;
    private Integer defaultProfile;
    private String serialId;
    private String addressId;
    private String assetId;

    private Integer status;



}
