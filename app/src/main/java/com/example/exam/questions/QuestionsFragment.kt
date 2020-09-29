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

        val application = requireNotNull(this.activity).application
        val questionsViewModelFactory = QuestionsViewModelFactory(application)
        val questionsViewModel = ViewModelProviders.of(this, questionsViewModelFactory).get(QuestionsViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.questionsViewModel = questionsViewModel

//        questionsViewModel.navigateToQuestions.observe(viewLifecycleOwner, Observer {
//                mode -> mode?.let {
//            this.findNavController().navigate(
//                StartFragmentDirections.actionStartFragmentToQuestionsFragment(mode)
//            )
//            questionsViewModel.doneNavigating()
//        }
//        })
        return binding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}