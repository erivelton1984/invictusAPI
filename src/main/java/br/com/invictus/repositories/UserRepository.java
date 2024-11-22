package br.com.invictus.repositories;


import br.com.invictus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT um FROM UserModel um WHERE um.userName LIKE LOWER(CONCAT ('%',:userName,'%'))")
    UserModel findByUserName(@Param("userName") String userName);

    @Query("SELECT um FROM UserModel um WHERE (:userName IS NULL OR LOWER(um.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) " +
            "OR (:email IS NULL OR LOWER(um.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    UserModel findByUserNameOrEmail (@Param("userName") String userName, @Param("email") String email);
}