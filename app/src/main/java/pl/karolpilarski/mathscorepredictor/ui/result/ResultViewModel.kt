package pl.karolpilarski.mathscorepredictor.ui.result

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import pl.karolpilarski.mathscorepredictor.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    val readingResult = dataRepository.readingResult
    val writingResult = dataRepository.writingResult
    val mathResult = dataRepository.mathResult

    val summary = combine(readingResult, writingResult, mathResult) { reading, writing, math ->
        var result: Double? = null

        reading?.let { readingRes ->
            result = readingRes.toDoubleOrNull() ?: 0.5
        }

        writing?.let { writingRes ->
            result?.let { res ->
                result = (res + (writingRes.toDoubleOrNull() ?: 0.5)) / 2
            } ?: run {
                result = writingRes.toDouble()
            }
        }

        math?.let { mathRes ->
            result?.let { res ->
                result = (res + (mathRes.toDoubleOrNull() ?: 0.5)) / 2
            } ?: run {
                result = mathRes.toDouble()
            }
        }

        result?.let {
            when (it) {
                in 0.0..0.4 -> {
                    ResultSummary.NOT_PASSED
                }

                in 0.4..0.6 -> {
                    ResultSummary.NEUTRAL
                }

                else -> ResultSummary.PASSED
            }
        } ?: ResultSummary.NEUTRAL
    }

    fun back() {
        dataRepository.clear()
    }
}

enum class ResultSummary {
    PASSED,
    NOT_PASSED,
    NEUTRAL
}