package br.com.invictus.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "calendar")
public class CalendarModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_event")
    private String title;

    @Column(name = "local_date_starts")
    private LocalDateTime start;

    @Column(name = "local_date_ends")
    private LocalDateTime end;

    @Column(name = "description")
    private String description;

    public CalendarModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalendarModel calendar = (CalendarModel) o;
        return Objects.equals(id, calendar.id) && Objects.equals(title, calendar.title) && Objects.equals(start, calendar.start) && Objects.equals(end, calendar.end) && Objects.equals(description, calendar.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, start, end, description);
    }
}