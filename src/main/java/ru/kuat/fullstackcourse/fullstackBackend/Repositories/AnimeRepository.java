package ru.kuat.fullstackcourse.fullstackBackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
}
