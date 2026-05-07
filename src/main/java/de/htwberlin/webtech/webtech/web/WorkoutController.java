package de.htwberlin.webtech.webtech.web;

import de.htwberlin.webtech.webtech.model.Workout;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*import org.springframework.http.ResponseEntity;*/
import java.util.List;

@RestController
public class WorkoutController {

    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return List.of(
            new Workout(1L, "Leg Day",    "2026-05-01", 60),
            new Workout(2L, "Upper Body", "2026-05-02", 45),
            new Workout(3L, "Cardio",     "2026-05-03", 30)
        );
    }
}