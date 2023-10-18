package ru.kuat.fullstackcourse.fullstackBackend.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Anime;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Episode;
import ru.kuat.fullstackcourse.fullstackBackend.Services.AnimeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimeController {
    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/anime")
    public List<Anime> getAllAnime(){
        return animeService.findAll();
    }

    @GetMapping("/anime/{animeId}/episodes")
    public ResponseEntity<Set<Episode>> getAllEpisodesByAnimeId(@PathVariable int animeId){
        Set<Episode> episodeSet = animeService.getAllEpisodesByAnimeId(animeId);
        return new ResponseEntity<>(episodeSet, HttpStatus.OK);
    }

    @GetMapping("/anime/{animeId}/characters")
    public ResponseEntity<Set<Character>> getAllCharactersByAnimeId(@PathVariable int animeId){
        Set<Character> characters = animeService.getAllCharactersByAnimeId(animeId);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @PostMapping("/anime")
    public HttpEntity<Anime> createAnime(@RequestBody Anime anime){
        Anime createdAnime = animeService.createAnime(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnime);
    }

    @GetMapping("/anime/{id}")
    public ResponseEntity<Anime> getOneAnimeById(@PathVariable int id){
        return ResponseEntity.ok(animeService.findOne(id));
    }

    @PutMapping("/anime/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable int id, @RequestBody Anime anime){
        return ResponseEntity.ok(animeService.update(id, anime));
    }

    @DeleteMapping("/anime/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable int id){
        animeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/anime/{animeId}/episodes")
    public ResponseEntity<Anime> addEpisodeToAnime(@PathVariable int animeId, @RequestBody Episode episode){
        Anime anime = animeService.addEpisodeToAnime(animeId, episode);
        return new ResponseEntity<>(anime, HttpStatus.CREATED);
    }

    @PostMapping("/anime/{animeId}/characters")
    public ResponseEntity<Anime> addCharacterToAnime(@PathVariable int animeId, @RequestBody Character character){
        Anime anime = animeService.addCharacterToAnime(animeId, character);
        return new ResponseEntity<>(anime, HttpStatus.CREATED);
    }

    @DeleteMapping("/anime/{animeId}/episodes/{episodeId}")
    public ResponseEntity<Anime> removeEpisodeFromAnime(@PathVariable int animeId, @PathVariable int episodeId){
        Anime anime = animeService.removeEpisodeFromAnime(animeId, episodeId);
        return new ResponseEntity<>(anime, HttpStatus.OK);
    }

    @DeleteMapping("/anime/{animeId}/characters/{charId}")
    public ResponseEntity<Anime> removeCharacterFromAnime(@PathVariable int animeId, @PathVariable int charId){
        Anime anime = animeService.removeCharacterFromAnime(animeId, charId);
        return new ResponseEntity<>(anime, HttpStatus.OK);
    }
}
