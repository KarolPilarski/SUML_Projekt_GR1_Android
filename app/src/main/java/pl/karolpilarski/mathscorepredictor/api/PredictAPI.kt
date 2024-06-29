package pl.karolpilarski.mathscorepredictor.api

import pl.karolpilarski.mathscorepredictor.data.GenericInput
import pl.karolpilarski.mathscorepredictor.data.PredictionModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PredictAPI {
    @POST("/predictReading")
    suspend fun getReadingPrediction(
        @Body input: GenericInput
    ): Response<String>

    @POST("/predictWriting")
    suspend fun getWritingPrediction(
        @Body input: GenericInput
    ): Response<String>

    @POST("/predictMath")
    suspend fun getMathPrediction(
        @Body input: GenericInput
    ): Response<String>
}