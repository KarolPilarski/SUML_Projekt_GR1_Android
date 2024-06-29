package pl.karolpilarski.mathscorepredictor.api

import pl.karolpilarski.mathscorepredictor.ConnectionConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConnectionConstants.BACKEND_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}