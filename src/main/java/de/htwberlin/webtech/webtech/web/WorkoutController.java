package de.htwberlin.webtech.webtech.web;

import de.htwberlin.webtech.webtech.model.Workout;
import de.htwberlin.webtech.webtech.model.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutRepository repository;

    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return repository.findAll();
    }

    @PostMapping("/workouts")
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        return ResponseEntity.status(201).body(repository.save(workout));
    }
}