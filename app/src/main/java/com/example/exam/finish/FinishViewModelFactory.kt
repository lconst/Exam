package com.example.exam.finish

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exam.database.QuestionsDatabaseDao
import com.example.exam.questions.QuestionsViewModel

class FinishViewModelFactory(
    private val correctAnswers: Int,
    private val countAnswers: Int,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinishViewModel::class.java)) {
            return FinishViewModel(correctAnswers, countAnswers, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}