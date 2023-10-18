package ru.kuat.fullstackcourse.fullstackBackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;
import ru.kuat.fullstackcourse.fullstackBackend.Models.VoiceActor;

import java.util.List;

@Repository
public interface VoiceRepository extends JpaRepository<VoiceActor, Integer> {
    List<Character> findVoiceActorsByCharactersId(int charId);
}
