package com.anangkur.architectureplayground.page.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.architectureplayground.repository.data.Note
import com.anangkur.architectureplayground.R
import com.anangkur.architectureplayground.page.MainViewModel

class NoteViewHolder(
    itemView: View,
    private val mainViewModel: MainViewModel?,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(note: Note) {
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvNote = itemView.findViewById<TextView>(R.id.tv_note)
        tvNote.text = note.note
        tvDate.text = mainViewModel?.formatDate(note.date)
    }
}