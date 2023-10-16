package com.anangkur.architectureplayground.repository

interface LoginRepository {
    suspend fun validateInput(username: String, password: String): Boolean
    suspend fun authenticate(username: String, password: String): String
    suspend fun saveToken(token: String)
    suspend fun loadToken(): String?
}