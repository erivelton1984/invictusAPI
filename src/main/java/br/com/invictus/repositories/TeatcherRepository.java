package br.com.invictus.repositories;

import br.com.invictus.model.TeatcherModel;
import br.com.invictus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeatcherRepository extends JpaRepository<TeatcherModel, Long> {

    @Query("SELECT um FROM TeatcherModel um WHERE LOWER(um.firstNameTeatcher) LIKE LOWER(CONCAT('%', :firstNameTeatcher, '%'))")
    List<TeatcherModel> findByTeatcherName(@Param("firstNameTeatcher") String firstNameTeatcher);

    //@Query("SELECT um FROM TeatcherModel um WHERE um.emailTeatcher =:emailTeatcher")
    //TeatcherModel findByEmail(String emailTeatcher);

    @Query("SELECT t FROM TeatcherModel t WHERE LOWER(t.emailTeatcher) = LOWER(:emailTeatcher)")
    List<TeatcherModel> findByEmail(@Param("emailTeatcher") String emailTeatcher);

}
