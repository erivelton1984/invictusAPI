package br.com.invictus.repositories;

import br.com.invictus.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {

    @Query("SELECT p FROM ProjectModel p WHERE p.projectName LIKE LOWER(CONCAT ('%',:projectName,'%'))")
    ProjectModel findByProjectName(@Param("projectName") String projectName);
}
