package com.whitestudio.simplerecyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.whitestudio.sidejo.util.hide
import com.whitestudio.sidejo.util.show
import com.whitestudio.simplerecyclerview.databinding.ActivityMainBinding
import com.whitestudio.simplerecyclerview.model.Students
import com.whitestudio.sidejo.viewmodel.Result

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private var studentsList = ArrayList<Students>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()
        binding.rvData.adapter = adapter
        viewModel.fetchStudentsList()
        observe()
    }

    private fun observe() {
        viewModel.fetchStudentsListResult.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    studentsList = result.value
                    if (studentsList.isEmpty())
                        binding.LLDataEmpty.show()
                    else
                        binding.LLDataEmpty.hide()
                    adapter.submitList(studentsList)
                }
                is Result.Failure -> {
                    if (!isFinishing) {
                        Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
    }
}