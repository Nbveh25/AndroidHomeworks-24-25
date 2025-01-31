package com.example.homeworks.util

import kotlinx.coroutines.*
import android.util.Log

class CoroutineManager {
    private val activeJobs = mutableListOf<Job>()
    private var scope = CoroutineScope(SupervisorJob())

    fun startCoroutines(
        count: Int,
        isSequential: Boolean,
        dispatcher: CoroutineDispatcher,
        onComplete: () -> Unit
    ) {
        val dispatcherName = when (dispatcher) {
            Dispatchers.IO -> "IO"
            Dispatchers.Main -> "Main"
            Dispatchers.Default -> "Default"
            else -> "Unknown"
        }
        Log.d("CoroutineManager", "Using dispatcher: $dispatcherName")

        if (isSequential) {
            val job = scope.launch(dispatcher) {
                Log.d("CoroutineManager", "Starting sequential coroutines")
                repeat(count) {
                    delay(2000)
                    Log.d("CoroutineManager", "Coroutine $it completed")
                }
            }
            activeJobs.add(job)

            job.invokeOnCompletion {
                if (it == null) {
                    Log.d("CoroutineManager", "Sequential coroutines completed")
                }
            }
        } else {
            Log.d("CoroutineManager", "Starting parallel coroutines")
            repeat(count) {
                val job = scope.launch(dispatcher) {
                    delay(2000)
                    Log.d("CoroutineManager", "Coroutine $it completed")
                }
                activeJobs.add(job)
            }
            Log.d("CoroutineManager", "All parallel coroutines started")
        }
        onComplete()
    }

    fun cancelCoroutines(): Int {
        val runningJobs = activeJobs.count { it.isActive }
        Log.d("CoroutineManager", "Cancelling $runningJobs coroutines")
        activeJobs.forEach { it.cancel() }
        activeJobs.clear()
        scope.cancel()
        scope = CoroutineScope(SupervisorJob())
        return runningJobs
    }
}