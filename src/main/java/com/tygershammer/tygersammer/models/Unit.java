package com.tygershammer.tygersammer.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Unit {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "unit")
    private Collection<UnitReview> unitReview;

    public Unit(){}

    public Unit(String name) {
        this.name = name;
    }

    public Unit(String name, Collection<UnitReview> unitReview) {
        this.name = name;
        this.unitReview = unitReview;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UnitReview> getUnitReview() {
        return unitReview;
    }

    public void setUnitReview(Collection<UnitReview> unitReview) {
        this.unitReview = unitReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return getName().equals(unit.getName()) && Objects.equals(getUnitReview(), unit.getUnitReview());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUnitReview());
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitReview=" + unitReview +
                '}';
    }
}
