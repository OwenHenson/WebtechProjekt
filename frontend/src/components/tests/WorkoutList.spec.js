import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import WorkoutList from '../WorkoutList.vue'

function flushPromises() {
  return new Promise(resolve => setTimeout(resolve, 0))
}

describe('WorkoutList', () => {
  beforeEach(() => {
    global.fetch = vi.fn()
  })

  it('renders workouts fetched on mount', async () => {
    global.fetch.mockResolvedValueOnce({
      json: () => Promise.resolve([
        { id: 1, name: 'Running', date: '2026-06-29', durationMinutes: 30 }
      ])
    })

    const wrapper = mount(WorkoutList)
    await flushPromises()

    expect(wrapper.text()).toContain('Running')
    expect(wrapper.text()).toContain('30 Minuten')
  })

  it('submits the form and adds the new workout to the list', async () => {
    global.fetch
      .mockResolvedValueOnce({ json: () => Promise.resolve([]) })
      .mockResolvedValueOnce({ json: () => Promise.resolve({ id: 2, name: 'Cycling', date: '2026-06-30', durationMinutes: 45 }) })

    const wrapper = mount(WorkoutList)
    await flushPromises()

    await wrapper.find('input[placeholder="Workout name"]').setValue('Cycling')
    await wrapper.find('input[type="date"]').setValue('2026-06-30')
    await wrapper.find('input[type="number"]').setValue(45)
    await wrapper.find('form').trigger('submit.prevent')
    await flushPromises()

    expect(wrapper.text()).toContain('Cycling')
    expect(global.fetch).toHaveBeenCalledWith(
      expect.stringContaining('/workouts'),
      expect.objectContaining({ method: 'POST' })
    )
  })
})