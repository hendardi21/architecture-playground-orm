package com.anangkur.architectureplayground.page.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.architectureplayground.R
import com.anangkur.architectureplayground.databinding.ActivityLoginBinding
import com.anangkur.architectureplayground.page.MainActivity
import com.anangkur.architectureplayground.repository.local.LocalRepository

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(
                    loginRepository = LocalRepository(
                        sharedPreferences = getSharedPreferences(
                            LocalRepository.PREF_NAME,
                            MODE_PRIVATE,
                        ),
                    )
                ) as T
            }
        }.create(LoginViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.view = binding?.root

        viewModel?.authentication?.observe(this) { token ->
            if (token.isNotEmpty() && token.isNotBlank()) {
                viewModel?.saveToken(token)
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
        }

        viewModel?.error?.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel?.checkLogin()
    }
}