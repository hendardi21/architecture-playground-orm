package com.anangkur.architectureplayground.page

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.anangkur.architectureplayground.repository.data.Note
import com.anangkur.architectureplayground.page.adapter.NoteAdapter
import com.anangkur.architectureplayground.R
import com.anangkur.architectureplayground.databinding.ActivityMainBinding
import com.anangkur.architectureplayground.page.auth.login.LoginActivity
import com.anangkur.architectureplayground.repository.local.LocalRepository
import com.anangkur.architectureplayground.repository.remote.RemoteRepository

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(
                    local = LocalRepository(
                        sharedPreferences = getSharedPreferences(
                            LocalRepository.PREF_NAME,
                            MODE_PRIVATE,
                        )
                    )
                ) as T
            }
        }.create(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        binding?.viewModel = mainViewModel
        binding?.view = binding?.root
        binding?.adapter = NoteAdapter(mainViewModel)
        binding?.layoutManager = LinearLayoutManager(this)

        mainViewModel?.logout?.observe(this) { isLoggedOut ->
            if (isLoggedOut) {
                startActivity(Intent(this, LoginActivity::class.java))
                this.finish()
            }
        }
    }
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, noteAdapter: NoteAdapter?) {
    recyclerView.adapter = noteAdapter
}

@BindingAdapter("setLayoutManager")
fun setLayoutManager(recyclerView: RecyclerView, layoutManager: LayoutManager) {
    recyclerView.layoutManager = layoutManager
}

@SuppressLint("NotifyDataSetChanged")
@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<Note>?) {
    (recyclerView.adapter as NoteAdapter).submitList(list)
    (recyclerView.adapter as NoteAdapter).notifyDataSetChanged()
}