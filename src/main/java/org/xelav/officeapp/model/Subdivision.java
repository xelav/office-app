package org.xelav.officeapp.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "workers"})
@DiscriminatorValue("subdivision")
public class Subdivision extends TreeElement {

    @Column(name="name")
    private String name;

    @Column(name="director_name")
    private String directorName;

    public List<Subdivision> getSubdivisions() {
        return subdivisions;
    }

    public void setSubdivisions(List<Subdivision> subdivisions) {
        this.subdivisions = subdivisions;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "subdivisions", "workers"})
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    private TreeElement treeParent;

    @OneToMany(
            mappedBy = "treeParent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("name ASC")
    private List<Subdivision> subdivisions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public TreeElement getTreeParent() {
        return treeParent;
    }

    public void setTreeParent(TreeElement treeParent) {
        this.treeParent = treeParent;
    }

    @Override
    public String toString() {
        return "Subdivision{" +
                "name='" + name + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
