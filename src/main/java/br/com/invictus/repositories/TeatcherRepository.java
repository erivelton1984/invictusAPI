package br.com.invictus.repositories;

import br.com.invictus.model.TeatcherModel;
import br.com.invictus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeatcherRepository extends JpaRepository<TeatcherModel, Long> {

    @Query("SELECT um FROM TeatcherModel um WHERE um.firstNameTeatcher LIKE LOWER(CONCAT ('%',:firstNameTeatcher,'%'))")
    List<TeatcherModel> findByTeatcherName(@Param("firstNameTeatcher") String firstNameTeatcher);
}
