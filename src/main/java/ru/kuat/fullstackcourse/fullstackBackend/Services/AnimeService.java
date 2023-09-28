package ru.kuat.fullstackcourse.fullstackBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return foundAnime.orElse(null);
    }

    @Transactional
    public void save(Anime anime){
        animeRepository.save(anime);
    }

    @Transactional
    public void update(int id, Anime updated){
        updated.setId(id);
        animeRepository.save(updated);
    }

    @Transactional
    public void delete(int id){
        animeRepository.deleteById(id);
    }
}
