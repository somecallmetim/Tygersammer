package com.tygershammer.tygersammer.models;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    Long id;
    @Column(unique = true, nullable = false)
    String hashtag;

    @ManyToMany(mappedBy = "hashtags")
    Set<Unit> units;

    protected Hashtag(){}

    public Hashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Hashtag(String hashtag, Set<Unit> units) {
        this.hashtag = hashtag;
        this.units = units;
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
