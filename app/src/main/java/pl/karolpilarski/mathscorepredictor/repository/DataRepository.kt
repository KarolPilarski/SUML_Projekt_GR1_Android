package pl.karolpilarski.mathscorepredictor.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.karolpilarski.mathscorepredictor.api.PredictAPI
import pl.karolpilarski.mathscorepredictor.api.RetrofitHelper
import pl.karolpilarski.mathscorepredictor.data.GenericInput
import pl.karolpilarski.mathscorepredictor.data.Model
import pl.karolpilarski.mathscorepredictor.data.PredictionDataAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor() {

    val isProcessing = MutableStateFlow(false)

    val readingResult = MutableStateFlow<String?>(null)
    val writingResult = MutableStateFlow<String?>(null)
    val mathResult = MutableStateFlow<String?>(null)

    private val api: PredictAPI = RetrofitHelper.getInstance().create(PredictAPI::class.java)

    fun start(predictionData: Map<String, Float>) {
        CoroutineScope(Dispatchers.IO).launch {
            isProcessing.emit(true)

            getReadingPrediction(predictionData)
            getWritingPrediction(predictionData)
            getMathPrediction(predictionData)
        }
    }

    fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            readingResult.emit(null)
            writingResult.emit(null)
            mathResult.emit(null)
            isProcessing.emit(false)
        }
    }

    private fun getReadingPrediction(predictionData: Map<String, Float>) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                println(GenericInput(
                    Model.READING.path,
                    predictionData
                ))

                api.getReadingPrediction(
                    GenericInput(
                        Model.READING.path,
                        predictionData
                    )
                )
            }.onSuccess { result ->
                readingResult.emit(
                    if (result.isSuccessful) {
                        result.body() ?: "-"
                    } else {
                        "-"
                    }
                )
            }.onFailure {
                readingResult.emit("-")
            }

            isProcessing.emit(false)
        }
    }

    private fun getWritingPrediction(predictionData: Map<String, Float>) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                api.getWritingPrediction(
                    GenericInput(
                        Model.WRITING.path,
                        predictionData
                    )
                )
            }.onSuccess { result ->
                writingResult.emit(
                    if (result.isSuccessful) {
                        result.body()?: "-"
                    } else {
                        "-"
                    }
                )
            }.onFailure {
                writingResult.emit("-")
            }

            isProcessing.emit(false)
        }
    }

    private fun getMathPrediction(predictionData: Map<String, Float>) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                api.getMathPrediction(
                    GenericInput(
                        Model.MATH.path,
                        predictionData
                    )
                )
            }.onSuccess { result ->
                println(result)
                mathResult.emit(
                    if (result.isSuccessful) {
                        result.body() ?: "-"
                    } else {
                        "-"
                    }
                )
            }.onFailure {
                println(it)
                mathResult.emit("-")
            }

            isProcessing.emit(false)
        }
    }
}
