package br.com.invictus.repositories;

import br.com.invictus.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UrlRepository extends JpaRepository<UrlModel, Long> {

    /*@Modifying
    @Query("UPDATE UrlModel um SET um.enabled = false WHERE um.id =:id")
    void disableUrl(@Param("id") Long id);*/

    @Query ("SELECT um FROM UrlModel um WHERE LOWER(um.nomeUrl) LIKE LOWER(CONCAT('%', :nomeUrl, '%'))")
    List<UrlModel> findUrlByName(@Param("nomeUrl") String nomeUrl);

    @Query("SELECT um FROM UrlModel um WHERE um.idUsuario =:idUsuario")
    List<UrlModel> findUrlByIdUser(@Param("idUsuario") Long idUsuario);
}
