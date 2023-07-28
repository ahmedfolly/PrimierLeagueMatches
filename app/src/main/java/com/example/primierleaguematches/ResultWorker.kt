package com.example.primierleaguematches

import okhttp3.ResponseBody
import java.lang.Exception

sealed class ResultWorker<out T> {
    object Loading : ResultWorker<Nothing>()
    data class Success<out T>(val data: T) : ResultWorker<T>()
    data class Error(val e: String) : ResultWorker<Nothing>()
}
