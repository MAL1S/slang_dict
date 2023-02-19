package com.example.slang_dict.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Insert
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.slang_dict.R
import com.example.slang_dict.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment: Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)

    private val viewModel: ListViewModel by viewModels()

    private val adapter = SlangWordAdapter {
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(it)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.rcvWords.adapter = adapter
        binding.etSearch.addTextChangedListener {
            viewModel.getFilteredWords(it.toString())
        }

        lifecycleScope.launchWhenStarted {
            viewModel.slangWords.collect {
                adapter.words = it
                binding.rcvWords.adapter?.notifyDataSetChanged()
            }
        }

        viewModel.getAllWords()
    }
}