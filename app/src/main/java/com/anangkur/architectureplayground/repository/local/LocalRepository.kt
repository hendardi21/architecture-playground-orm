package com.anangkur.architectureplayground.repository.local

import android.content.SharedPreferences
import com.anangkur.architectureplayground.repository.LoginRepository
import com.anangkur.architectureplayground.repository.data.Note
import com.anangkur.architectureplayground.repository.MainRepository
import kotlinx.coroutines.delay
import java.util.Date

class LocalRepository(
    private val sharedPreferences: SharedPreferences,
) : MainRepository, LoginRepository {

    companion object {
        private const val KEY_TOKEN = "token"
        const val PREF_NAME = "sharedPref"
    }

    private var i = 0

    private val notesData = mutableListOf<Note>()

    override fun provideNotes(): List<Note> {
        return notesData
    }

    override fun addNote(): List<Note> {
        val note = Note(Date(), "note: $i")
        notesData.add(note)
        i++
        return provideNotes()
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    override suspend fun validateInput(username: String, password: String): Boolean {
        delay(3000)
        return username.isNotEmpty()
                && username.isNotBlank()
                && password.isNotEmpty()
                && password.isNotBlank()
    }

    override suspend fun authenticate(username: String, password: String): String {
        return if (username == "anangkur" && password == "123456") {
            "token"
        } else {
            throw UnsupportedOperationException("username dan password salah!")
        }
    }

    override suspend fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override suspend fun loadToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
}