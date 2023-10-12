package ru.kuat.fullstackcourse.fullstackBackend.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Anime;
import ru.kuat.fullstackcourse.fullstackBackend.Services.AnimeService;

import java.util.List;

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
}
