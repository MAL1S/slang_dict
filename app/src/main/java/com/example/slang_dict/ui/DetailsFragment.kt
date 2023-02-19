package com.example.slang_dict.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.slang_dict.R
import com.example.slang_dict.data.local.SlangWord
import com.example.slang_dict.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    val adapter = TranslateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg: DetailsFragmentArgs by navArgs()
        val word = arg.slangWord

        init(word)
    }

    private fun init(word: SlangWord) {
        binding.detailsBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.detailsTvWord.text = word.word

        adapter.translates = word.translates.split(",")
        adapter.translates.forEach {
            it.replaceFirstChar { char ->
                char.uppercase()
            }
        }
        binding.rcvTranslates.adapter = adapter
    }
}