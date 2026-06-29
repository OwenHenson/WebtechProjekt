package de.htwberlin.webtech.webtech.web;

import de.htwberlin.webtech.webtech.model.Workout;
import de.htwberlin.webtech.webtech.model.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkoutController.class)
class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutRepository repository;

    @Test
    void getWorkouts_returnsListFromRepository() throws Exception {
        Workout w = new Workout("Running", "2026-06-29", 30);
        w.setId(1L);
        when(repository.findAll()).thenReturn(List.of(w));

        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Running"))
                .andExpect(jsonPath("$[0].durationMinutes").value(30));
    }

    @Test
    void createWorkout_savesAndReturns201() throws Exception {
        Workout saved = new Workout("Cycling", "2026-06-30", 45);
        saved.setId(2L);
        when(repository.save(any(Workout.class))).thenReturn(saved);

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Cycling\",\"date\":\"2026-06-30\",\"durationMinutes\":45}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Cycling"));
    }
}