package ru.kuat.fullstackcourse.fullstackBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.AnimeNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.EpisodeNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Episode;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.AnimeRepository;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.EpisodeRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final AnimeRepository animeRepository;
    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository, AnimeRepository animeRepository) {
        this.episodeRepository = episodeRepository;
        this.animeRepository = animeRepository;
    }

    public Episode getEpisodeById(int episodeId){
        return episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EpisodeNotFoundException("This episode does not exist with this id: " + episodeId));
    }

    public List<Episode> getEpisodesByAnimeId(int animeId){
        return episodeRepository.findByAnimeId(animeId);
    }

    @Transactional
    public Episode addEpisode(Episode episode){
        return episodeRepository.save(episode);
    }

    @Transactional
    public Episode updateEpisode(int episodeId, Episode updated){
        Episode foundEpisode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EpisodeNotFoundException("This episode does not exist with this id: " + episodeId));
        foundEpisode.setTitle(updated.getTitle());
        foundEpisode.setEpisodeNumber(updated.getEpisodeNumber());
        return episodeRepository.save(foundEpisode);
    }

    @Transactional
    public void deleteEpisode(int episodeId){
        episodeRepository.deleteById(episodeId);
    }
}
