package com.example.exam.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.database.QuestionsDatabase
import com.example.exam.databinding.ContentQuestionsBinding
import com.example.exam.databinding.FragmentQuestionsBinding
import com.example.exam.databinding.FragmentStartBinding
import com.example.exam.start.StartFragmentDirections
import com.example.exam.start.StartViewModel
import com.example.exam.start.StartViewModelFactory

class QuestionsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentQuestionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false)

        val arguments = QuestionsFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val database = QuestionsDatabase.getInstance(application).questionsDatabaseDao
        val questionsViewModelFactory = QuestionsViewModelFactory(arguments.mode, database, application)
        val questionsViewModel = ViewModelProviders.of(this, questionsViewModelFactory).get(QuestionsViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.questionsViewModel = questionsViewModel


        questionsViewModel.navigateToFinish.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val action = QuestionsFragmentDirections.actionQuestionsFragmentToFinishFragment()
                action.setCorrectAnswers(questionsViewModel.correctAnswers)
                action.setCountAnswers(questionsViewModel.countAnswers)
                action.setCountQuestions(questionsViewModel.countAnswers)
                this.findNavController().navigate(action)
                questionsViewModel.doneNavigating()
            }
        })
        return binding.root

    }
}