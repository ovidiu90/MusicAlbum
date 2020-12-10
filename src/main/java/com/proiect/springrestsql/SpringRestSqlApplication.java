package com.proiect.springrestsql;

import com.proiect.springrestsql.controller.SongController;
import com.proiect.springrestsql.model.Song;
import com.proiect.springrestsql.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringRestSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestSqlApplication.class, args);
    }



@Component
class DemoCommandLineRunner implements CommandLineRunner{
    @Autowired
    private SongRepository songRepository;

    @Override
    public void run(String... args) throws Exception {
        printMenu();
    }

    public void printMenu() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - Quit\n" +
                "1 - Print all songs in database\n" +
                "2 - Populate database\n" +
                "3 - Insert new song\n" +
                "4 - Delete song\n\n");

        Long dbSize = songRepository.count();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action){
                case 0:
                    System.out.println("Menu closed.");
                    quit = true;
                    break;

                case 1:
                    dbSize = songRepository.count();
                    if (dbSize == 0) {
                        System.out.println("Database is empty.");
                    } else {
                        List<Song> songList = songRepository.findAll();
                        for (Song song : songList) {
                            System.out.println(song);
                        }
                    }
                    break;

                case 2:
                    if (dbSize == 0){
                        Song song0 = new Song("Track001", 5.45, 2005);
                        Song song1 = new Song("Track002", 3.45, 2015);
                        Song song2 = new Song("Track003", 5.15, 2020);
                        Song song3 = new Song("Track004", 5.45, 1995);
                        Song song4 = new Song("Track005", 2.33, 1999);
                        Song song5 = new Song("Track006", 4.18, 2003);

                        songRepository.save(song0);
                        songRepository.save(song1);
                        songRepository.save(song2);
                        songRepository.save(song3);
                        songRepository.save(song4);
                        songRepository.save(song5);
                        System.out.println("Songs successfully added to empty DB.");
                    } else {
                        System.out.println("Database is not empty.");
                    }
                    break;

                case 3:
                    System.out.println("Insert song name: \n");
                    String name = scanner.nextLine();
                    System.out.println("Insert song length: \n");
                    double length = Double.parseDouble(scanner.nextLine());
                    System.out.println("Insert song year: \n");
                    int year = Integer.parseInt(scanner.nextLine());

                    Song newSong = new Song(name, length, year);
                    songRepository.save(newSong);
                    System.out.println("Song added to DataBase.");
                    break;

                case 4:
                    System.out.println("Insert song name: \n");
                    String songName = scanner.nextLine();

                    List<Song> songList = songRepository.findAll();
                    for (Song song : songList) {
                        if (song.getName().equals(songName)){
                            songRepository.delete(song);
                            System.out.println("Song "+songName+" deleted.");
                            break;
                        }
                    }
                    break;

            }
        }
    }
}}
