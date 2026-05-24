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
      workouts: []
    }
  },
  mounted() {
    fetch('https://<YOUR-BACKEND-URL>/workouts')
      .then(res => res.json())
      .then(data => { this.workouts = data })
      .catch(err => console.error('Fetch error:', err))
  }
}
</script>