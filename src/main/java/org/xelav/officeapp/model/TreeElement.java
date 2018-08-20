package org.xelav.officeapp.model;

import javax.persistence.*;

@Entity
@Table(name="tree_element",
        uniqueConstraints={
        @UniqueConstraint(columnNames = {"parent_id", "name"})}
        )
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ELEMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
public class TreeElement extends BaseModel {

}
