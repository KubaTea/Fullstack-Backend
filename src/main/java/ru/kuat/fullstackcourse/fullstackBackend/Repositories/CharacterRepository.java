package ru.kuat.fullstackcourse.fullstackBackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    List<Character> findByAnimeId(int animeId);
    List<Character> findCharactersByVoiceActorsId(int voiceId);
}
