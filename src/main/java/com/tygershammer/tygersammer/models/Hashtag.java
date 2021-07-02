package com.tygershammer.tygersammer.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    Long id;
    @Column(unique = true, nullable = false)
    String hashtag;

    @ManyToMany
    @JoinTable(name = "hastag_unit",
            joinColumns = @JoinColumn(name = "hashtag_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    Set<Unit> units = new HashSet<Unit>();

    protected Hashtag(){}

    public Hashtag(String hashtag, Unit unit) {
        this.hashtag = hashtag;
        units.add(unit);
    }

    public Long getId() {
        return id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit){this.units.add(unit);}

    public void removeUnit(Unit unit){this.units.remove(unit);}

    public boolean checkIfUnitInSet(Unit unit){return this.units.contains(unit);}

    public boolean checkIfUnitsSetEmpty(){return this.units.isEmpty();}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hashtag)) return false;
        Hashtag hashtag1 = (Hashtag) o;
        return getHashtag().equals(hashtag1.getHashtag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHashtag());
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", hashtag='" + hashtag + '\'' +
                ", units=" + units.toString() +
                '}';
    }
}
