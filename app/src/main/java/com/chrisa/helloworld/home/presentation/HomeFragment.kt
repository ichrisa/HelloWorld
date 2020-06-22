package com.chrisa.helloworld.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chrisa.helloworld.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadGreeting()

        viewModel.greetingResource.observe(this.viewLifecycleOwner, Observer {
            val greetingResource = it ?: return@Observer
            when (greetingResource) {
                is GreetingResource.Success -> {
                    renderGreeting(greetingResource.greeting)
                }
                GreetingResource.Error -> {
                    renderError()
                }
            }
        })
    }

    private fun renderError() {
        Snackbar.make(
            requireView(),
            R.string.greeting_error,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun renderGreeting(greeting: String) {
        textField.text = greeting
    }
}
