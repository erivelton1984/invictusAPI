package br.com.invictus.repositories;

import br.com.invictus.model.StudentModel;
import br.com.invictus.model.TeatcherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    @Query("SELECT em FROM StudentModel em WHERE em.studentName LIKE LOWER(CONCAT ('%',:studentName,'%'))")
    List<StudentModel> findByStudentName(@Param("studentName") String studentName);

    @Query("SELECT s FROM StudentModel s WHERE s.projectId = :projectId")
    List<StudentModel> findStudentByProjectId(@Param("projectId") Long projectId);
}
