package br.com.invictus.controller;

import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Endopint de Listagem of belts.")
@RestController
@RequestMapping("/api/invictus/degree/v1")
public class DegreeController {

    @GetMapping
    public List<DegreeController.DegreeDTO> getBelts() {
        return Arrays.stream(DegreeENUM.values())
                .map(degree -> new DegreeController.DegreeDTO(degree.getId(), degree.getDescription()))
                .collect(Collectors.toList());
    }

    private record DegreeDTO(Integer id, String description) {}
}
