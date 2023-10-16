package com.anangkur.architectureplayground.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.anangkur.architectureplayground.repository.data.Note
import com.anangkur.architectureplayground.R
import com.anangkur.architectureplayground.page.MainViewModel

class NoteAdapter(private val mainViewModel: MainViewModel?) : ListAdapter<Note, NoteViewHolder>(
    NoteDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false,
            ),
            mainViewModel = mainViewModel,
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}