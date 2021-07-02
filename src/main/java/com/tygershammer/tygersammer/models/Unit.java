package com.tygershammer.tygersammer.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Unit {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "unit", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Collection<UnitReview> unitReviews;

    @ManyToMany(mappedBy = "units")
    private Set<Hashtag> hashtags = new HashSet<Hashtag>();

    public Unit(){}

    public Unit(String name) {
        this.name = name;
    }

    public Unit(String name, Collection<UnitReview> unitReviews, Set<Hashtag> hashtags) {
        this.name = name;
        this.unitReviews = unitReviews;
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

    public Collection<UnitReview> getUnitReviews() {
        return unitReviews;
    }

    public void setUnitReviews(Collection<UnitReview> unitReviews) {
        this.unitReviews = unitReviews;
    }

    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public void addHashtag(Hashtag hashtag){
        hashtags.add(hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return getId().equals(unit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
