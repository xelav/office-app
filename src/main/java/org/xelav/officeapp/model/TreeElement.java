package org.xelav.officeapp.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ELEMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
public class TreeElement extends BaseModel {

}
