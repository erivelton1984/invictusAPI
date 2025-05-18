package br.com.invictus.repositories;

import br.com.invictus.model.CalendarModel;
import br.com.invictus.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository extends JpaRepository <CalendarModel, Long> {

    @Query("SELECT cm FROM CalendarModel cm WHERE cm.title LIKE LOWER(CONCAT ('%',:title,'%'))")
    List<StudentModel> findByTitle(@Param("title") String title);
}
