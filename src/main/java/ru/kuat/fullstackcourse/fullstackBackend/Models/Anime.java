package ru.kuat.fullstackcourse.fullstackBackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String title;

    @Column(name = "episodes")
    @Min(value = 0, message = "Episodes should be more than 0")
    private int episodes;

    @Column(name = "dateofrelease")
    private String dateOfRelease;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Episode> episodeSet= new HashSet<>();
    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Character> characters = new HashSet<>();

    public Anime(){}

    public Anime(String title, int episodes, String dateOfRelease) {
        this.title = title;
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<Episode> getEpisodeSet() {
        return episodeSet;
    }

    public void setEpisodeSet(Set<Episode> episodeSet) {
        this.episodeSet = episodeSet;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public void addCharacter(Character character){
        this.characters.add(character);
    }

    public void removeCharacter(int charId){
        Character character = this.characters.stream().filter(c -> c.getId() == charId).
                findFirst().orElse(null);
        if (character != null){
            this.characters.remove(character);
        }
    }

    public void addEpisode(Episode episode){
        this.episodeSet.add(episode);
    }

    public void removeEpisode(int episodeId){
        Episode episode = this.episodeSet.stream().filter(e -> e.getId() == episodeId).
                findFirst().orElse(null);
        if (episode != null){
            this.episodeSet.remove(episode);
        }
    }
}
