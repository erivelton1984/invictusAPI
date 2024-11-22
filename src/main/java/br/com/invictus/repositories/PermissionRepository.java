package br.com.invictus.repositories;


import br.com.invictus.model.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {

    PermissionModel findByDescription(String nivel);

    Optional<PermissionModel> findById(Long id);
}
