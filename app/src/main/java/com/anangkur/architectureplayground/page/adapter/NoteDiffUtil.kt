package com.anangkur.architectureplayground.page.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anangkur.architectureplayground.repository.data.Note

class NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.date == newItem.date
    }
}