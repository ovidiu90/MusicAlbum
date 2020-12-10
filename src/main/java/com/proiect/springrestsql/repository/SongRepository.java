package com.proiect.springrestsql.repository;

import com.proiect.springrestsql.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
