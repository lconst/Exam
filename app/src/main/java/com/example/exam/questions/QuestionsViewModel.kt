package com.example.exam.questions

import android.app.Application
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exam.R
import com.example.exam.database.DatabaseConstants
import com.example.exam.database.Question
import com.example.exam.database.QuestionsDatabaseDao
import kotlinx.android.synthetic.main.fragment_questions.view.*
import kotlinx.coroutines.*
import java.lang.Exception

class QuestionsViewModel(
    examMode: Int,
    val database: QuestionsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToFinish = MutableLiveData<Boolean>()

    val navigateToFinish: LiveData<Boolean>
        get() = _navigateToFinish

    private var question = MutableLiveData<Question?>()

    val questionString = MutableLiveData<String?>()

    val variant_1_Srting = MutableLiveData<String?>()
    val variant_1_visible = MutableLiveData<Int>()
    val variant_1_color = MutableLiveData<Int>()
    val variant_1_checked = MutableLiveData<Boolean>()

    val variant_2_Srting = MutableLiveData<String?>()
    val variant_2_visible = MutableLiveData<Int>()
    val variant_2_color = MutableLiveData<Int>()
    val variant_2_checked = MutableLiveData<Boolean>()

    val variant_3_Srting = MutableLiveData<String?>()
    val variant_3_visible = MutableLiveData<Int>()
    val variant_3_color = MutableLiveData<Int>()
    val variant_3_checked = MutableLiveData<Boolean>()

    val variant_4_Srting = MutableLiveData<String?>()
    val variant_4_visible = MutableLiveData<Int>()
    val variant_4_color = MutableLiveData<Int>()
    val variant_4_checked = MutableLiveData<Boolean>()

    val variant_5_Srting = MutableLiveData<String?>()
    val variant_5_visible = MutableLiveData<Int>()
    val variant_5_color = MutableLiveData<Int>()
    val variant_5_checked = MutableLiveData<Boolean>()

    val checkVisible = MutableLiveData<Int>()
    val nextVisible = MutableLiveData<Int>()

    val questionId = MutableLiveData<String>()

    private val indexesOfQuestion = mutableListOf<Int>()
    private var _answer = 0

    var correctAnswers : Int
    var countAnswers : Int

    init {
        indexesOfQuestion.addAll(0, (1..DatabaseConstants.NUMBER_OF_QUESTIONS).toList())
        if (examMode == 1) {
            indexesOfQuestion.shuffle()
        }
        initQuestion()
        correctAnswers = 0
        countAnswers = 0
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun initQuestion() {
        try {
            val key = indexesOfQuestion.first()
            uiScope.launch {
                question.value = getQuestionFromDatabase(key)
                if (question.value != null) {
                    indexesOfQuestion.remove(key)
                    questionString.value = question.value!!.questionName

                    variant_1_Srting.value = question.value!!.variantOne
                    variant_1_visible.value = if (variant_1_Srting.value == null)View.GONE else View.VISIBLE

                    variant_2_Srting.value = question.value!!.variantTwo
                    variant_2_visible.value = if (variant_2_Srting.value == null)View.GONE else View.VISIBLE

                    variant_3_Srting.value = question.value!!.variantThree
                    variant_3_visible.value = if (variant_3_Srting.value == null)View.GONE else View.VISIBLE

                    variant_4_Srting.value = question.value!!.variantFour
                    variant_4_visible.value = if (variant_4_Srting.value == null)View.GONE else View.VISIBLE

                    variant_5_Srting.value = question.value!!.variantFive
                    variant_5_visible.value = if (variant_5_Srting.value == null)View.GONE else View.VISIBLE

                    checkVisible.value = View.VISIBLE
                    nextVisible.value = View.GONE

                    variant_1_color.value = Color.BLACK
                    variant_2_color.value = Color.BLACK
                    variant_3_color.value = Color.BLACK
                    variant_4_color.value = Color.BLACK
                    variant_5_color.value = Color.BLACK

                    _answer = 1

                    questionId.value = "Вопрос №: $key"

                    variant_1_checked.value = true
                    variant_2_checked.value = false
                    variant_3_checked.value = false
                    variant_4_checked.value = false
                    variant_5_checked.value = false
                }
            }
        } catch (e: Exception) {
            Log.d("Questions", "Questions ending!!!")
            onFinish()
        }
    }

    private suspend fun getQuestionFromDatabase(key: Int): Question? {
        return withContext(Dispatchers.IO) {
            Log.d("getQuestion", "getQuestion")
            val question = database.get(key)
            question
        }
    }

    fun onSetAnswer(view: View) {
        when (view.id) {
           R.id.variant_1 -> _answer = 1
           R.id.variant_2 -> _answer = 2
           R.id.variant_3 -> _answer = 3
           R.id.variant_4 -> _answer = 4
           R.id.variant_5 -> _answer = 5
        }
    }

    fun onCheck() {
        if (_answer != question.value?.answerId) {
            when (_answer) {
                1 -> variant_1_color.value = Color.RED
                2 -> variant_2_color.value = Color.RED
                3 -> variant_3_color.value = Color.RED
                4 -> variant_4_color.value = Color.RED
                5 -> variant_5_color.value = Color.RED
            }
        } else {
            correctAnswers++
        }
        when(question.value?.answerId) {
            1 -> variant_1_color.value = Color.GREEN
            2 -> variant_2_color.value = Color.GREEN
            3 -> variant_3_color.value = Color.GREEN
            4 -> variant_4_color.value = Color.GREEN
            5 -> variant_5_color.value = Color.GREEN
        }
        checkVisible.value = View.GONE
        nextVisible.value = View.VISIBLE
        countAnswers++
    }

    fun onNext() {
        _answer = 0
        questionString.value = null
        variant_1_Srting.value = null
        variant_2_Srting.value = null
        variant_3_Srting.value = null
        variant_4_Srting.value = null
        variant_5_Srting.value = null

        initQuestion()
    }

    fun onFinish() {
        _navigateToFinish.value = true
    }

    fun doneNavigating() {
        _navigateToFinish.value = false
    }
}