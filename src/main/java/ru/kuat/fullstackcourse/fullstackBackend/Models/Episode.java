package ru.kuat.fullstackcourse.fullstackBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @NotEmpty
    private String title;
    @Column(name = "episodenumber")
    private int episodeNumber;
    @ManyToOne
    @JoinColumn(name = "anime_id", referencedColumnName = "id")
    @JsonIgnore
    private Anime anime;

    public Episode(){}

    public Episode(String title, int episodeNumber, Anime anime) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.anime = anime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
