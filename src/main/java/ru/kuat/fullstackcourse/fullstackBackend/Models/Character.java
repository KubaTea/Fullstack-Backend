package ru.kuat.fullstackcourse.fullstackBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    @NotEmpty(message = "Name should not be empty")
    private String fullName;
    @Column(name = "age")
    @Min(value = 0, message = "Age should be less than 0")
    private int age;
    @ManyToOne
    @JoinColumn(name = "anime_id", referencedColumnName = "id")
    @JsonIgnore
    private Anime anime;

    @ManyToMany(mappedBy = "characters")
    @JsonIgnore
    private Set<VoiceActor> voiceActors = new HashSet<>();

    public Character(){}

    public Character(String fullName, int age, Anime anime) {
        this.fullName = fullName;
        this.age = age;
        this.anime = anime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Set<VoiceActor> getVoiceActors() {
        return voiceActors;
    }

    public void setVoiceActors(Set<VoiceActor> voiceActors) {
        this.voiceActors = voiceActors;
    }

    public void addVoice(VoiceActor voiceActor){
        this.voiceActors.add(voiceActor);
        voiceActor.getCharacters().add(this);
    }

    public void removeVoice(int voiceId){
        VoiceActor voiceActor = this.voiceActors.stream().filter(v -> v.getId() == voiceId).
                findFirst().orElse(null);
        if (voiceActor != null){
            this.voiceActors.remove(voiceActor);
            voiceActor.getCharacters().remove(this);
        }
    }
}
