package com.proiect.springrestsql.controller;

import com.proiect.springrestsql.eception.ResourceNotFoundException;
import com.proiect.springrestsql.model.Song;
import com.proiect.springrestsql.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/music")
    public List<Song> getSongs(){
       return songRepository.findAll();
    }

    @GetMapping("/music/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) throws ResourceNotFoundException {
        Song song = songRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Can not find song with id: "+id));
        return ResponseEntity.ok().body(song);
    }

    @DeleteMapping("/music/{id}")
    public void deleteById(@PathVariable Long id) throws ResourceNotFoundException{
        Song song = songRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Can not delete song with id: " + id)
        );
        songRepository.delete(song);
    }

    @PostMapping("/music")
    public Song createSong(@Valid @RequestBody Song song){
        Song newSong = songRepository.save(song);
        return newSong;
    }

    @PutMapping("/music/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @Valid @RequestBody Song newSong) throws ResourceNotFoundException{
        Song oldSong = songRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Update failed. Can not find song with id: " + id)
        );
        oldSong.setName(newSong.getName());
        oldSong.setDuration(newSong.getDuration());
        oldSong.setYear(newSong.getYear());
        final Song updatedSong = songRepository.save(oldSong);

        return ResponseEntity.ok(updatedSong);

    }


}
