package com.example.exam.questions

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exam.database.QuestionsDatabaseDao
import com.example.exam.start.StartViewModel

class QuestionsViewModelFactory(
    private val examMode: Int,
    private val dataSource: QuestionsDatabaseDao,
    private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            return QuestionsViewModel(examMode, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}