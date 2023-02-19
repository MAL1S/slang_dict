package com.example.slang_dict.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.slang_dict.R
import com.example.slang_dict.data.local.SlangWord
import com.example.slang_dict.databinding.ItemSlangWordBinding
import com.example.slang_dict.databinding.ItemWordDetailsBinding

class TranslateAdapter: RecyclerView.Adapter<TranslateAdapter.ViewHolder>() {
    inner class ViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_word_details, parent, false)) {

        private val binding by viewBinding(ItemWordDetailsBinding::bind)

        fun bind(translate: String) {
            binding.tvTranslate.text = translate
        }
    }

    var translates = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val translate = translates[position]
        holder.bind(translate)
    }

    override fun getItemCount() = translates.size
}