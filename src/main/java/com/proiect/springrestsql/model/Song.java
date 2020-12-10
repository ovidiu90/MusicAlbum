package com.proiect.springrestsql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "songs")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Song {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "song_seq", sequenceName = "song_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_seq")
    private long id;
    private String name;
    private double duration;
    private int year;

    public Song(String name, double duration, int year) {
        this.name = name;
        this.duration = duration;
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Song name: " + name +
                ", duration = " + duration +
                ", year = " + year + '.';
    }
}
