package br.com.invictus.repositories;


import br.com.invictus.model.ProjectModel;
import br.com.invictus.model.TechniqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TechniqueRepository extends JpaRepository<TechniqueModel, Long> {

    @Query("SELECT p FROM TechniqueModel p WHERE p.techniqueName LIKE LOWER(CONCAT ('%',:techniqueName,'%'))")
    ProjectModel findByProjectName(@Param("techniqueName") String techniqueName);
}
