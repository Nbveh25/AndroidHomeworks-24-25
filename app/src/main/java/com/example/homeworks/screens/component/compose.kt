package com.example.homeworks.screens.component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.homeworks.R
import com.example.homeworks.util.Constants
import com.example.homeworks.util.CoroutineManager
import kotlinx.coroutines.*

@Composable
fun CoroutineScreen(context: Context) {
    var coroutineCount by remember { mutableStateOf("") }
    var isSequential by remember { mutableStateOf(true) }
    var cancelOnMinimize by remember { mutableStateOf(true) }
    var selectedDispatcher by remember { mutableStateOf("Default") }
    val dispatchers = listOf("Default", "IO", "Main")
    var expanded by remember { mutableStateOf(false) }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val coroutineManager = remember { CoroutineManager() }
    var canceledCount by remember { mutableIntStateOf(0) }

    DisposableEffect(Unit) {
        val lifecycleOwner = context as LifecycleOwner
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP && cancelOnMinimize) {
                canceledCount = coroutineManager.cancelCoroutines()
                snackbarMessage = if (canceledCount > 0) {
                    context.resources.getString(R.string.coroutines_canceled, canceledCount)
                } else {
                    context.resources.getString(R.string.no_coroutines_to_cancel)
                }
                snackbarVisible = true
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = coroutineCount,
            onValueChange = { coroutineCount = it },
            label = {
                Text(stringResource(R.string.input_count_coroutine))
            },
            isError = coroutineCount.isNotEmpty() && !coroutineCount.all { it.isDigit() },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.mode),
                    style = MaterialTheme.typography.h6
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.sequentially))
                    RadioButton(
                        selected = isSequential,
                        onClick = {
                            isSequential = true
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.parallel))
                    RadioButton(
                        selected = !isSequential,
                        onClick = {
                            isSequential = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.onMinimize),
                    style = MaterialTheme.typography.h6
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.cancel_work))
                    RadioButton(
                        selected = cancelOnMinimize,
                        onClick = {
                            cancelOnMinimize = true
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.continue_work))
                    RadioButton(
                        selected = !cancelOnMinimize,
                        onClick = {
                            cancelOnMinimize = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.choose_pool),
                    style = MaterialTheme.typography.h6
                )
                Button(
                    onClick = {
                        expanded = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = selectedDispatcher)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    dispatchers.forEach { pool ->
                        DropdownMenuItem(onClick = {
                            selectedDispatcher = pool
                            expanded = false
                        }) {
                            Text(pool)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (coroutineCount.isEmpty() || !coroutineCount.all { it.isDigit() }) {
                    snackbarMessage = context.resources.getString(R.string.invalid_coroutine_count)
                    snackbarVisible = true
                } else {
                    val count = coroutineCount.toInt()
                    val dispatcher = when (selectedDispatcher) {
                        Constants.IO -> Dispatchers.IO
                        Constants.MAIN -> Dispatchers.Main
                        else -> Dispatchers.Default
                    }
                    try {
                        coroutineManager.startCoroutines(
                            count = count,
                            isSequential = isSequential,
                            dispatcher = dispatcher
                        ) {
                            snackbarMessage = context.resources.getString(R.string.coroutines_started, count)
                            snackbarVisible = true
                        }
                    } catch (e: Exception) {
                        snackbarMessage = context.resources.getString(R.string.coroutine_start_error, e.message)
                        snackbarVisible = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.coroutine_run))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val canceledCount = coroutineManager.cancelCoroutines()
                snackbarMessage = if (canceledCount > 0) {
                    context.resources.getString(R.string.coroutines_canceled, canceledCount)
                } else {
                    context.resources.getString(R.string.no_coroutines_to_cancel)
                }
                snackbarVisible = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.coroutine_cancel))
        }
        if (snackbarVisible) {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                action = {
                    Button(
                        onClick = {
                            snackbarVisible = false
                        }
                    ) {
                        Text(stringResource(R.string.snackbar_close))
                    }
                }
            ) {
                Text(snackbarMessage)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface {
        CoroutineScreen(context = LocalContext.current)
    }
}