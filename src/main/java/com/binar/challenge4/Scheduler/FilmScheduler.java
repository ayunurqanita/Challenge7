package com.binar.challenge4.Scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.binar.challenge4.model.Film;
import com.binar.challenge4.model.Schedule;
import com.binar.challenge4.repository.FilmRepository;
import com.binar.challenge4.repository.FilmScheduleRepository;
import com.binar.challenge4.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FilmScheduler {

    @Autowired
    private  FilmRepository filmRepository;

    @Autowired
    private FilmScheduleRepository filmScheduleRepository;

    // untuk memperbarui status film
    @Scheduled(cron = "0 0 8 * * *")
    public void updateFilmStatus() {
        // Ambil daftar film dengan status isPremiered = false
        List<Film> films = filmRepository.findFilmByIsPremiered(false);

        for (Film film : films) {
            // Set status film menjadi true
            film.setIsPremiered(true);
            // Simpan perubahan ke dalam repository
            filmRepository.save(film);
        }

        System.out.println("==================================================");
        System.out.println("Update status film pada: " + LocalDateTime.now());
        System.out.println("==================================================");
        }

    @Scheduled(cron = "0 0 8 * * *") // Menjalankan pada pukul 08:00 setiap hari
    public void displayCurrentFilmSchedule() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        List<Schedule> currentFilmSchedules = filmScheduleRepository.findCurrentFilmSchedules(currentDate, currentTime);

        for (Schedule schedule : currentFilmSchedules) {
            System.out.println("Film yang sedang tayang saat ini:");
            System.out.println("Film ID: " + schedule.getFilm().getFilmCode());
            System.out.println("Film Title: " + schedule.getFilm().getFilmName());
            System.out.println("Start Hour: " + schedule.getStartHour());
            System.out.println("End Hour: " + schedule.getEndHour());
            System.out.println("Price: " + schedule.getPrice());
            System.out.println("----------------------------------------");
        }
    }



//    @Scheduled(cron = "0 0 10 * * MON-FRI") // Menjalankan setiap hari Senin-Jumat pukul 10:00 AM
//    public void addFilmSchedule() {
//        FilmScheduler filmSchedule = new FilmSchedule();
//        filmSchedule.setFilmId(123); // ID film yang ingin ditambahkan jadwal penayangannya
//        filmSchedule.setScheduleTime(LocalDateTime.now()); // Waktu penayangan baru
//        filmService.addFilmSchedule(filmSchedule);
//        System.out.println("Menambah penayangan film baru pada: " + LocalDateTime.now());
//    }
//
//    private Long setFilmId(int id) {
//
//    }
//
//
//    // Memperbarui jadwal penayangan film
//    @Scheduled(cron = "0 0 12 * * MON-FRI") // Menjalankan setiap hari Senin-Jumat pukul 12:00 PM
//    public void updateFilmSchedule() {
//        // Kode untuk memperbarui jadwal penayangan film
//        // ...
//        System.out.println("Memperbarui jadwal penayangan film pada: " + LocalDateTime.now());
//    }
//
//    // Menghapus penayangan film yang sudah berakhir
//    @Scheduled(cron = "0 0 8 * * MON-FRI") // Menjalankan setiap hari Senin-Jumat pukul 8:00 AM
//    public void removeExpiredFilmSchedule() {
//        // Kode untuk menghapus penayangan film yang sudah berakhir
//        // ...
//        System.out.println("Menghapus penayangan film yang sudah berakhir pada: " + LocalDateTime.now());
//    }
//
//    // Mengatur harga tiket berdasarkan waktu atau jenis penayangan
//    @Scheduled(cron = "0 0 15 * * MON-FRI") // Menjalankan setiap hari Senin-Jumat pukul 3:00 PM
//    public void setTicketPrice() {
//        // Kode untuk mengatur harga tiket berdasarkan waktu atau jenis penayangan
//        // ...
//        System.out.println("Mengatur harga tiket pada: " + LocalDateTime.now());
//    }

}

