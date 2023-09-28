package ru.kuat.fullstackcourse.fullstackBackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Anime")
public class Anime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 150, message = "Title should be between 2 and 150 characters")
    private String name;

    @Column(name = "episodes")
    @Min(value = 0, message = "Episodes should be more than 0")
    private int episodes;

    @Column(name = "dateofrelease")
    private String dateOfRelease;

    public Anime(){}

    public Anime(String name, int episodes, String dateOfRelease) {
        this.name = name;
        this.episodes = episodes;
        this.dateOfRelease = dateOfRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }
}
