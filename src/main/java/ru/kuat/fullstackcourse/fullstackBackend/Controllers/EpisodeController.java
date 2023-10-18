package ru.kuat.fullstackcourse.fullstackBackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Episode;
import ru.kuat.fullstackcourse.fullstackBackend.Services.EpisodeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EpisodeController {
    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/episodes/{animeId}")
    public List<Episode> getEpisodesByAnimeId(@PathVariable int animeId){
        return episodeService.getEpisodesByAnimeId(animeId);
    }

    @GetMapping("/episode/{episodeId}")
    public Episode getEpisode(@PathVariable int episodeId){
        return episodeService.getEpisodeById(episodeId);
    }

    @PostMapping("/episode")
    public HttpEntity<Episode> createEpisode(@RequestBody Episode episode){
        Episode createdEpisode = episodeService.addEpisode(episode);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEpisode);
    }

    @PutMapping("/episode/{episodeId}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable int episodeId, @RequestBody Episode episode){
        return ResponseEntity.ok(episodeService.updateEpisode(episodeId, episode));
    }

    @DeleteMapping("/episode/{episodeId}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable int episodeId){
        episodeService.deleteEpisode(episodeId);
        return ResponseEntity.noContent().build();
    }
}
