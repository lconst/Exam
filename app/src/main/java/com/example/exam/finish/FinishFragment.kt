package com.example.exam.finish

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
import com.example.exam.databinding.FragmentFinishBinding
import com.example.exam.questions.QuestionsFragmentArgs
import com.example.exam.questions.QuestionsFragmentDirections
import com.example.exam.questions.QuestionsViewModel
import com.example.exam.questions.QuestionsViewModelFactory

class FinishFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFinishBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish, container, false)

        val arguments = FinishFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val finishViewModelFactory = FinishViewModelFactory(arguments.correctAnswers, arguments.countAnswers, application)
        val finishViewModel = ViewModelProviders.of(this, finishViewModelFactory).get(
            FinishViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.finishViewModel = finishViewModel

        finishViewModel.navigateToStart.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val action = FinishFragmentDirections.actionFinishFragmentToStartFragment()
                this.findNavController().navigate(action)
                finishViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}