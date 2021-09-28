package com.futurebrains.retrofitnoteexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.futurebrains.retrofitnoteexample.databinding.ActivityMainBinding
import com.futurebrains.retrofitnoteexample.utils.adapter.TodoAdapter
import com.futurebrains.retrofitnoteexample.utils.api.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

const val TAG = " MainActivity "

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var noteadapter : TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response  = try {
                RetrofitInstance.api.getTitle()
            }catch (e : IOException){
                Log.e(TAG, "IOException, You Might Not Have Internet Connection... " )
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            catch (e : HttpException){
                Log.e(TAG, "HttpException , Some Error Occur while Loading From Web " )
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null ){
                noteadapter.TodoItems = response.body()!!
            }
            else  Log.e(TAG, "HttpException , Some Error Occur while Loading From Web " )

                binding.progressBar.isVisible = false
        }



    }

    private fun setupRecyclerView() = binding.rvRecyclerview.apply {
        noteadapter = TodoAdapter()
        adapter = noteadapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}