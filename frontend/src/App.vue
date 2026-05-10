<template>
  <div>
    <h1>Workout Logger</h1>
    <WorkoutList />
  </div>
</template>

<script>
import WorkoutList from './components/WorkoutList.vue'
export default {
  components: { WorkoutList }
}
</script>