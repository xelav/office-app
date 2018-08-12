package org.xelav.officeapp.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@SuppressWarnings("serial")
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private long id;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}