package com.tygershammer.tygersammer.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UnitReview {
    @Id @GeneratedValue
    private Long id;
    @Lob @Column(nullable = false)
    private String reviewText;
    @ManyToOne
    private Unit unit;

    protected UnitReview(){}

    public UnitReview(String reviewText, Unit unit) {
        this.reviewText = reviewText;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitReview)) return false;
        UnitReview that = (UnitReview) o;
        return getReviewText().equals(that.getReviewText()) && getUnit().equals(that.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewText(), getUnit());
    }

    @Override
    public String toString() {
        return "UnitReview{" +
                "id=" + id +
                ", reviewText='" + reviewText + '\'' +
                ", unit=" + unit +
                '}';
    }
}
