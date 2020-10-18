package com.example.exam.finish

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exam.database.QuestionsDatabaseDao

class FinishViewModel(
    correctAnswers: Int,
    countAnswers: Int,
    application: Application
) : AndroidViewModel(application) {
    val totalResultString = MutableLiveData<String>()
    init
    {
        val percent = if (countAnswers != 0) correctAnswers.toDouble() / countAnswers.toDouble() * 100 else 0.0
        totalResultString.value = ("Правильных ответов $correctAnswers из $countAnswers вопросов (" + String.format("%.2f", percent) + " %)")
    }

    private val _navigateToStart = MutableLiveData<Boolean>()

    val navigateToStart: LiveData<Boolean>
        get() = _navigateToStart


    fun onOk() {
        _navigateToStart.value = true
    }

    fun doneNavigating() {
        _navigateToStart.value = false
    }

}