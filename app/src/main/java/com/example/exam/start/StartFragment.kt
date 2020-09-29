package com.example.exam.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentStartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)

        val application = requireNotNull(this.activity).application
        val startViewModelFactory = StartViewModelFactory(application)
        val startViewModel = ViewModelProviders.of(this, startViewModelFactory).get(StartViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.startViewModel = startViewModel

        startViewModel.navigateToQuestions.observe(viewLifecycleOwner, Observer {
            mode -> mode?.let {
            this.findNavController().navigate(
                StartFragmentDirections.actionStartFragmentToQuestionsFragment(mode)
            )
            startViewModel.doneNavigating()
        }
        })
        return binding.root
    }
}