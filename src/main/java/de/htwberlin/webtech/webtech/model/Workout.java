package de.htwberlin.webtech.webtech.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workout {
    private Long id;
    private String name;
    private String date;
    private int durationMinutes;

    public Workout(Long id, String name, String date, int durationMinutes) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.durationMinutes = durationMinutes;
    }
}