package ru.kuat.fullstackcourse.fullstackBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.CharacterNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Exceptions.VoiceNotFoundException;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;
import ru.kuat.fullstackcourse.fullstackBackend.Models.VoiceActor;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.CharacterRepository;
import ru.kuat.fullstackcourse.fullstackBackend.Repositories.VoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final VoiceRepository voiceRepository;
    @Autowired
    public CharacterService(CharacterRepository characterRepository, VoiceRepository voiceRepository) {
        this.characterRepository = characterRepository;
        this.voiceRepository = voiceRepository;
    }

    public List<Character> getAllCharactersByAnimeId(int animeId){
        return characterRepository.findByAnimeId(animeId);
    }

    public Character getCharacterById(int id){
        return characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException("Character with this id does not exist: " + id));
    }

    @Transactional
    public Character createCharacter(Character character){
        return characterRepository.save(character);
    }

    @Transactional
    public Character updateCharacter(int id, Character updated){
        Character characterFound = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException("Character with this id does not exist" + id));
        characterFound.setFullName(updated.getFullName());
        characterFound.setAge(updated.getAge());
        return characterRepository.save(characterFound);
    }

    @Transactional
    public void deleteCharacter(int id){
        characterRepository.deleteById(id);
    }


}
