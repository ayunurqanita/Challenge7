package com.binar.challenge4.repository;

import com.binar.challenge4.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FilmScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.premieredDate = :date AND s.startHour <= :time AND s.endHour >= :time")

    List<Schedule> findCurrentFilmSchedules(LocalDate date, LocalTime time);
}
