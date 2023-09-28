package ru.kuat.fullstackcourse.fullstackBackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
