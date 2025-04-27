package br.com.invictus.controller;

import br.com.invictus.enums.BeltENUM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Endopint for listing of belts.")
@RestController
@RequestMapping("/api/invictus/belt/v1")
public class BeltController {

    @Operation(summary = "Get all belts.")
    @GetMapping
    public List<BeltDTO> getBelts() {
        return Arrays.stream(BeltENUM.values())
                .map(belt -> new BeltDTO(belt.getId(), belt.getDescription()))
                .collect(Collectors.toList());
    }

    private record BeltDTO(Integer id, String description) {}
}