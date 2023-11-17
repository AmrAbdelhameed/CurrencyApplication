package com.example.currencyapplication.data.repository

import android.app.Application
import com.example.currencyapplication.R
import com.example.currencyapplication.data.source.remote.Resource
import com.example.currencyapplication.domain.model.remote.ErrorResponse
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository(
    private val application: Application
) {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else {
                    val baseError: ErrorResponse? = convertErrorBody(response.errorBody())
                    Resource.Error(
                        errorMessage = baseError?.error?.info
                            ?: application.getString(R.string.errorMsg)
                    )
                }

            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: application.getString(R.string.errorMsg))
            } catch (e: IOException) {
                Resource.Error(application.getString(R.string.connectionError))
            } catch (e: Exception) {
                Resource.Error(errorMessage = application.getString(R.string.errorMsg))
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}