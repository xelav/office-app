package org.xelav.officeapp.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // json config
@DiscriminatorValue("subdivision")
public class Subdivision extends TreeElement {

    @Column(name="name")
    private String name;

    @Column(name="director_name")
    private String directorName;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @NotNull
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"subdivisions"})
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    private TreeElement treeParent;

    @OneToMany(
            mappedBy = "subdivision",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Worker> workers = new ArrayList<>();

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

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Subdivision{" +
                "name='" + name + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
