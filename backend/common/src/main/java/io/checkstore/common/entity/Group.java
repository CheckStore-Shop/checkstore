package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import one.util.streamex.StreamEx;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder
@Table(value = CommonConstants.EntityName.GROUP)
public class Group extends AbstractJdbcEntity<Long>{
    private String name;
    private String description;
    private String status;


    @MappedCollection(idColumn = "group_id")
    private Set<GroupUserRef> userRefs;

    public Group(){
    }

    public Group(String name, String description, String status, Set<GroupUserRef> userRefs){
        this.name = name;
        this.description = description;
        this.status = status;
        this.userRefs = Objects.requireNonNullElse(userRefs, new HashSet<>());
    }

    private Set<Long> getUserIds() {
        return StreamEx.of(userRefs).map(GroupUserRef::getUserId).toImmutableSet();
    }

}
