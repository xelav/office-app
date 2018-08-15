package org.xelav.officeapp.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name="auto_gen", initialValue=20, allocationSize=20) // set initialValue to 20 to reserve some ids for DML scripts
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private long id;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}