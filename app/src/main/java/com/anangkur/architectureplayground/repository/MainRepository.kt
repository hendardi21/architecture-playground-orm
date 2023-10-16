package com.anangkur.architectureplayground.repository

import com.anangkur.architectureplayground.repository.data.Note

interface MainRepository {
    fun provideNotes(): List<Note>
    fun addNote(): List<Note>
    fun clearToken()
}