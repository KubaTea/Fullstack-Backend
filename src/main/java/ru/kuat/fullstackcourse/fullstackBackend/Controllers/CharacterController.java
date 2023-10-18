package ru.kuat.fullstackcourse.fullstackBackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;
import ru.kuat.fullstackcourse.fullstackBackend.Services.CharacterService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters/{charId}")
    public Character getCharacter(@PathVariable int charId){
        return characterService.getCharacterById(charId);
    }

    @GetMapping("/characters/{animeId}")
    public List<Character> getCharactersByAnimeId(@PathVariable int animeId){
        return characterService.getAllCharactersByAnimeId(animeId);
    }

    @PostMapping("/characters")
    public HttpEntity<Character> createCharacter(@RequestBody Character character){
        Character createdCharacter = characterService.createCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }

    @PutMapping("/characters/{charId}")
    public ResponseEntity<Character> updateCharacter(@PathVariable int charId, @RequestBody Character character){
        return ResponseEntity.ok(characterService.updateCharacter(charId, character));
    }

    @DeleteMapping("/characters/{charId}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable int charId){
        characterService.deleteCharacter(charId);
        return ResponseEntity.noContent().build();
    }
}
