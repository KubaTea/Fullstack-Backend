package ru.kuat.fullstackcourse.fullstackBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.AnimeNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.CharacterNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.EpisodeNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Anime;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Episode;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.AnimeRepository;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.CharacterRepository;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.EpisodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final EpisodeRepository episodeRepository;
    private final CharacterRepository characterRepository;


    @Autowired
    public AnimeService(AnimeRepository animeRepository, EpisodeRepository episodeRepository, CharacterRepository characterRepository) {
        this.animeRepository = animeRepository;
        this.episodeRepository = episodeRepository;
        this.characterRepository = characterRepository;
    }

    public List<Anime> findAll(){
        return animeRepository.findAll();
    }

    public Anime findOne(int id){
        Optional<Anime> foundAnime = animeRepository.findById(id);
        return foundAnime.orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + id));
    }

    public Set<Episode> getAllEpisodesByAnimeId(int id){
        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + id));
        return anime.getEpisodeSet();
    }

    public Set<Character> getAllCharactersByAnimeId(int id){
        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + id));
        return anime.getCharacters();
    }

    @Transactional
    public Anime createAnime(Anime anime){
        return animeRepository.save(anime);
    }

    @Transactional
    public Anime addEpisodeToAnime(int animeId, Episode episode){
        Anime anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + animeId));
        episode.setAnime(anime);
        anime.addEpisode(episode);
        episodeRepository.save(episode);

        return animeRepository.save(anime);
    }

    @Transactional
    public Anime addCharacterToAnime(int animeId, Character character){
        Anime anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + animeId));
        character.setAnime(anime);
        anime.addCharacter(character);
        characterRepository.save(character);

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

    @Transactional
    public Anime removeEpisodeFromAnime(int animeId, int episodeId){
        Anime anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + animeId));
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EpisodeNotFoundException("This episode does not exist with id: " + episodeId));
        if (anime.getEpisodeSet().remove(episode)){
            episode.setAnime(null);
            episodeRepository.delete(episode);
            anime.removeEpisode(episodeId);
        }
        return animeRepository.save(anime);
    }

    @Transactional
    public Anime removeCharacterFromAnime(int animeId, int charId){
        Anime anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new AnimeNotFoundException("This title does not exist with id: " + animeId));
        Character character = characterRepository.findById(charId)
                .orElseThrow(() -> new CharacterNotFoundException("This episode does not exist with id: " + charId));
        if (anime.getCharacters().remove(character)){
            character.setAnime(null);
            characterRepository.delete(character);
            anime.removeCharacter(charId);
        }
        return animeRepository.save(anime);
    }
}
