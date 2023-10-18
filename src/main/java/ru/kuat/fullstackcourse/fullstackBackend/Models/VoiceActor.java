package ru.kuat.fullstackcourse.fullstackBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Voice_actor")
public class VoiceActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    @NotEmpty(message = "Name should not be empty")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last name should not be empty")
    private String lastName;
    @Column(name = "year_of_production")
    @Min(value = 1900, message = "year should not be less than 1900")
    private int yearOfProduction;
    @ManyToMany
    @JoinTable(
            name = "Character_Voice",
            joinColumns = @JoinColumn(name = "voice_id"),
            inverseJoinColumns = @JoinColumn(name = "char_id")
    )
    @JsonIgnore
    private Set<Character> characters = new HashSet<>();

    public VoiceActor(){}

    public VoiceActor(String firstName, String lastName, int yearOfProduction) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfProduction = yearOfProduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public void addCharacter(Character character){
        this.characters.add(character);
        character.getVoiceActors().add(this);
    }

    public void removeCharacter(int charId){
        Character character = this.characters.stream().filter(c -> c.getId() == charId).
                findFirst().orElse(null);
        if (character != null){
            this.characters.remove(character);
            character.getVoiceActors().remove(this);
        }
    }
}
