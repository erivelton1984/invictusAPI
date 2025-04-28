package br.com.invictus.controller;


import br.com.invictus.data.vo.CalendarVO;
import br.com.invictus.repositories.CalendarRepository;
import br.com.invictus.services.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Endpoint for listing, creating, updating and deleting of events.")
@RestController
@RequestMapping("/api/invictus/events/v1")
public class CalendarController {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    CalendarService calendarService;

    @Operation(summary = "Endpoint for create a new event in calendar.")
    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody CalendarVO calendarVO) throws IOException {
        return calendarService.create(calendarVO);
    }

    @Operation(summary = "Endpoint for get all event in calendar.")
    @GetMapping
    public List<CalendarVO> getAllEvents() {
        return calendarService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable Long id) {
        calendarService.delete(id);
    }


}
