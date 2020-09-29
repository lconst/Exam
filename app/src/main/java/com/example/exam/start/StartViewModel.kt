package com.example.exam.start

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exam.R
import kotlinx.android.synthetic.main.fragment_start.view.*

class StartViewModel(application: Application): AndroidViewModel(application) {

    private val _navigateToQuestions = MutableLiveData<Int>()

    val navigateToQuestions: LiveData<Int>
    get() = _navigateToQuestions

    private val _mode = MutableLiveData<Int>()

    val mode: LiveData<Int>
    get() = _mode

    fun onSetMode(view: View) {
        when (view.id) {
            R.id.order_mode -> _mode.value = 0
            R.id.random_mode -> _mode.value = 1
        }
    }

    fun onStartExam() {
        _navigateToQuestions.value = 1
    }

    fun doneNavigating() {
        _navigateToQuestions.value = null
    }
}