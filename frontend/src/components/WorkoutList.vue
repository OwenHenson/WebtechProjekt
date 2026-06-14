<template>
  <div>
    <h2>My Workouts</h2>

    <form @submit.prevent="addWorkout">
      <input v-model="newWorkout.name" placeholder="Workout name" required />
      <input v-model="newWorkout.date" type="date" required />
      <input v-model.number="newWorkout.durationMinutes" type="number" placeholder="Duration (min)" required />
      <button type="submit">Add Workout</button>
    </form>

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
const API = 'https://webtechprojekt-backend.onrender.com'

export default {
  name: 'WorkoutList',
  data() {
    return {
      workouts: [],
      newWorkout: { name: '', date: '', durationMinutes: 0 }
    }
  },
  mounted() {
    fetch(`${API}/workouts`)
      .then(res => res.json())
      .then(data => { this.workouts = data })
      .catch(err => console.error('Fetch error:', err))
  },
  methods: {
    async addWorkout() {
      const response = await fetch(`${API}/workouts`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.newWorkout)
      })
      const saved = await response.json()
      this.workouts.push(saved)
      this.newWorkout = { name: '', date: '', durationMinutes: 0 }
    }
  }
}
</script>