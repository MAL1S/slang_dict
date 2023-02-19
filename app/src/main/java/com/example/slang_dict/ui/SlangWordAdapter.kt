package com.example.slang_dict.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.slang_dict.R
import com.example.slang_dict.data.local.SlangWord
import com.example.slang_dict.databinding.ItemSlangWordBinding

class SlangWordAdapter(
    private val onWordDetailsClickedListener: OnWordDetailsClickedListener
): RecyclerView.Adapter<SlangWordAdapter.ViewHolder>() {
    inner class ViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val onWordDetailsClickedListener: OnWordDetailsClickedListener
    ): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_slang_word, parent, false)) {

        private val binding by viewBinding(ItemSlangWordBinding::bind)

        fun bind(slangWord: SlangWord) {
            binding.cardTv.text = slangWord.word

            itemView.setOnClickListener {
                onWordDetailsClickedListener.onClicked(slangWord)
            }
        }
    }

    var words = listOf<SlangWord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent, onWordDetailsClickedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }

    override fun getItemCount() = words.size
}

fun interface OnWordDetailsClickedListener {
    fun onClicked(word: SlangWord)
}