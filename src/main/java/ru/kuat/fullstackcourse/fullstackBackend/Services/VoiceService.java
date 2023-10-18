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

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class VoiceService {
    private final VoiceRepository voiceRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public VoiceService(VoiceRepository voiceRepository, CharacterRepository characterRepository) {
        this.voiceRepository = voiceRepository;
        this.characterRepository = characterRepository;
    }

    public List<VoiceActor> getAllVoiceActors(){
        return voiceRepository.findAll();
    }

    public VoiceActor getVoiceById(int id){
        return voiceRepository.findById(id)
                .orElseThrow(() -> new VoiceNotFoundException("Voice Actor with this id does not exist: " + id));
    }

    public Set<Character> getAllCharactersByVoiceId(int id){
        VoiceActor voiceActor = voiceRepository.findById(id)
                .orElseThrow(() -> new VoiceNotFoundException("Voice Actor with this id does not exist: " + id));
        return voiceActor.getCharacters();
    }

    @Transactional
    public VoiceActor createVoice(VoiceActor voiceActor){
        return voiceRepository.save(voiceActor);
    }

    @Transactional
    public Character addCharacterToVoiceActor(int voiceId, Character charRequest){
        return voiceRepository.findById(voiceId).map(voiceActor -> {
            int charId = charRequest.getId();

            if (charId != 0){
                Character character = characterRepository.findById(charId)
                        .orElseThrow(() -> new CharacterNotFoundException("Character with this id does not exist: " + charId));
                voiceActor.addCharacter(character);
                voiceRepository.save(voiceActor);
                return character;
            }

            voiceActor.addCharacter(charRequest);
            return characterRepository.save(charRequest);
        }).orElseThrow(() -> new VoiceNotFoundException("Voice Actor with this id does not exist: " + voiceId));
    }

    @Transactional
    public VoiceActor updateVoice(int id, VoiceActor updated){
        VoiceActor foundVoice = voiceRepository.findById(id)
                .orElseThrow(() -> new VoiceNotFoundException("Voice Actor with this id does not exist: " + id));
        foundVoice.setFirstName(updated.getFirstName());
        foundVoice.setLastName(updated.getLastName());
        foundVoice.setYearOfProduction(updated.getYearOfProduction());
        return voiceRepository.save(foundVoice);
    }

    @Transactional
    public void deleteVoice(int id){
        voiceRepository.deleteById(id);
    }

    @Transactional
    public void deleteCharacterFromVoiceActor(int voiceId, int charId){
        VoiceActor voiceActor = voiceRepository.findById(voiceId)
                .orElseThrow(() -> new VoiceNotFoundException("Voice Actor with this id does not exist: " + voiceId));
        voiceActor.removeCharacter(charId);
        voiceRepository.save(voiceActor);
    }
}
