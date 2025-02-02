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
            Dispatchers.IO -> Constants.IO
            Dispatchers.Main -> Constants.MAIN
            Dispatchers.Default -> Constants.DEFAULT
            else -> Constants.UNKNOWN
        }
        Log.d("CoroutineManager", "Using dispatcher: $dispatcherName")

        if (isSequential) {
            val job = scope.launch(dispatcher) {
                repeat(count) { index ->
                    val innerJob = launch {
                        delay(2000)
                        Log.d("CoroutineManager", "Sequential coroutine $index completed")
                    }
                    activeJobs.add(innerJob)
                    innerJob.join()
                }
            }
            activeJobs.add(job)
        } else {
            Log.d("CoroutineManager", "Starting parallel coroutines")
            repeat(count) { index ->
                val job = scope.launch(dispatcher) {
                    delay(2000)
                    Log.d("CoroutineManager", "Parallel coroutine $index completed")
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