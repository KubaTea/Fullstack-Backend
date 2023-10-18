package ru.kuat.fullstackcourse.fullstackBackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuat.fullstackcourse.fullstackBackend.Models.Character;
import ru.kuat.fullstackcourse.fullstackBackend.Models.VoiceActor;
import ru.kuat.fullstackcourse.fullstackBackend.Services.VoiceService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class VoiceController {
    private final VoiceService voiceService;

    @Autowired
    public VoiceController(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @GetMapping("/voice")
    public List<VoiceActor> getAllVoiceActors(){
        return voiceService.getAllVoiceActors();
    }

    @GetMapping("/voice/{voiceId}")
    public ResponseEntity<VoiceActor> getVoiceActorById(@PathVariable int voiceId){
        return ResponseEntity.ok(voiceService.getVoiceById(voiceId));
    }

    @GetMapping("/voice/{voiceId}/characters")
    public ResponseEntity<Set<Character>>getAllCharactersByVoiceId(@PathVariable int voiceId){
        Set<Character> characters = voiceService.getAllCharactersByVoiceId(voiceId);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @PostMapping("/voice")
    public HttpEntity<VoiceActor> createVoiceActor(@RequestBody VoiceActor voiceActor){
        VoiceActor createdVoice = voiceService.createVoice(voiceActor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoice);
    }

    @PutMapping("/voice/{id}")
    public ResponseEntity<VoiceActor> updateVoiceActor(@PathVariable int id, @RequestBody VoiceActor voiceActor){
        return ResponseEntity.ok(voiceService.updateVoice(id, voiceActor));
    }

    @DeleteMapping("/voice/{id}")
    public ResponseEntity<Void> deleteVoiceActor(@PathVariable int id){
        voiceService.deleteVoice(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/voice/{voiceId}")
    public ResponseEntity<Character> addCharacterToVoiceActor(@PathVariable int voiceId, @RequestBody Character character){
        Character addedCharacter = voiceService.addCharacterToVoiceActor(voiceId, character);
        return new ResponseEntity<>(addedCharacter, HttpStatus.CREATED);
    }

    @DeleteMapping("/voice/{voiceId}/{charId}")
    public ResponseEntity<HttpStatus> deleteCharacterFromVoiceActor(@PathVariable int voiceId, @PathVariable int charId){
        voiceService.deleteCharacterFromVoiceActor(voiceId, charId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
