package ru.kuat.fullstackcourse.fullstackBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.AnimeNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Anime;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.AnimeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> findAll(){
        return animeRepository.findAll();
    }

    public Anime findOne(int id){
        Optional<Anime> foundAnime = animeRepository.findById(id);
        return foundAnime.orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + id));
    }

    @Transactional
    public Anime createAnime(Anime anime){
        return animeRepository.save(anime);
    }

    @Transactional
    public Anime update(int id, Anime updated){
        Anime foundAnime = animeRepository.findById(id)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + id));
        foundAnime.setTitle(updated.getTitle());
        foundAnime.setEpisodes(updated.getEpisodes());
        foundAnime.setDateOfRelease(updated.getDateOfRelease());
        return animeRepository.save(foundAnime);
    }

    @Transactional
    public void delete(int id){
        animeRepository.deleteById(id);
    }
}
