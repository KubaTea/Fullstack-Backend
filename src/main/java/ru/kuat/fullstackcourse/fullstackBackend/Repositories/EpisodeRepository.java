package ru.kuat.fullstackcourse.fullstackBackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Episode;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByAnimeId(int animeId);
}
