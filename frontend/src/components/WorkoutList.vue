<template>
  <div>
    <h2>My Workouts</h2>
    <ul>
      <li v-for="workout in workouts" :key="workout.id">
        <strong>{{ workout.name }}</strong> —
        {{ workout.date }} —
        {{ workout.durationMinutes }} Minuten
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'WorkoutList',
  data() {
    return {
      workouts: [
        { id: 1, name: 'Leg Day',    date: '2026-05-01', durationMinutes: 60 },
        { id: 2, name: 'Upper Body', date: '2026-05-02', durationMinutes: 45 },
        { id: 3, name: 'Cardio',     date: '2026-05-03', durationMinutes: 30 },
      ]
    }
  }
}
</script>