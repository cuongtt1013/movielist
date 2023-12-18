package com.example.movielist

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.adapter.SearchAdapter
import com.example.movielist.databinding.ActivityMainBinding
import com.example.movielist.share.SpacesItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapter: SearchAdapter by lazy { SearchAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initListener()
        initRecyclerView()

        fetchHistory()
    }

    private fun initRecyclerView() {
        binding.rcv.adapter = adapter
        val layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.rcv.layoutManager = layoutManager

        binding.rcv.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        if (binding.rcv.itemDecorationCount == 0) {
            binding.rcv.addItemDecoration(
                SpacesItemDecoration(
                    12,
                    12,
                    12,
                    12,
                    1,
                    1
                )
            )
        }
    }

    private fun initListener() {
        binding.imgSearch.setOnClickListener {
            fetchHistory()
        }

        binding.editTextSearch.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    fetchHistory()
                    return true
                }
                return false
            }
        })
    }

    private fun fetchHistory() {
        viewModel.searchKeyWord = binding.editTextSearch.text.toString()
        lifecycleScope.launch {
            viewModel.getMovie().collectLatest {
                adapter.submitData(PagingData.empty())
                adapter.submitData(it)
            }
        }
    }
}