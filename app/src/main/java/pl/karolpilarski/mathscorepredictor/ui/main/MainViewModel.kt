package pl.karolpilarski.mathscorepredictor.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import pl.karolpilarski.mathscorepredictor.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    dataRepository: DataRepository
) : ViewModel() {
    val currentState = combine(
        dataRepository.isProcessing,
        dataRepository.readingResult,
        dataRepository.writingResult,
        dataRepository.mathResult
    ) { processing, reading, writing, math ->
        when {
            processing -> MainState.LOADING

            reading != null || writing != null || math != null -> MainState.RESULT

            else -> MainState.READY
        }
    }
}

enum class MainState {
    READY,
    LOADING,
    RESULT
}