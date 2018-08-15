package org.xelav.officeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // json config
@DiscriminatorValue("office")
public class Office extends TreeElement {

    @Column(name="name", unique=true)
    private String name;

    @Column(name="address")
    private String address;

    @OneToMany(
            mappedBy = "treeParent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Subdivision> subdivisions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Subdivision> getSubdivisions() {
        return subdivisions;
    }

    public void setSubdivisions(List<Subdivision> subdivisions) {
        this.subdivisions = subdivisions;
    }

    @Override
    public String toString() {
        return "Office{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
