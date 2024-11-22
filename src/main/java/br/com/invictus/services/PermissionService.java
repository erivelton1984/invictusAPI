package br.com.invictus.services;

import br.com.invictus.model.PermissionModel;
import br.com.invictus.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<PermissionModel> getUserLevels() {
        List<PermissionModel> permissionModels = new ArrayList<>();
        permissionModels = permissionRepository.findAll();

        return permissionModels;
    }
}
