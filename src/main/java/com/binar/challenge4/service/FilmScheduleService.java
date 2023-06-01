package com.binar.challenge4.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.binar.challenge4.model.Schedule;
import com.binar.challenge4.repository.FilmScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmScheduleService {

    private final FilmScheduleRepository filmScheduleRepository;

    @Autowired
    public FilmScheduleService(FilmScheduleRepository filmScheduleRepository) {
        this.filmScheduleRepository = filmScheduleRepository;
    }

    public List<Schedule> getCurrentFilmSchedules() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return filmScheduleRepository.findCurrentFilmSchedules(currentDate, currentTime);
    }
}
