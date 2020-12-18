package com.example.notely.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notely.Data.viewmodel.NoteViewModel
import com.example.notely.R
import com.example.notely.adapters.RecyclerAdapter
import com.example.notely.databinding.FragmentListNoteBinding


class ListNote : Fragment(), SearchView.OnQueryTextListener {

    private val mNoteViewModel: NoteViewModel by viewModels()
    private val adapter: RecyclerAdapter by lazy { RecyclerAdapter() }
    private var _binding: FragmentListNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNoteBinding.inflate(inflater, container, false)
        //SetupRecyclerView
        setupRecyclerView()
        //observe liveData
        mNoteViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        //setup menu
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"

        mNoteViewModel.searchDatabase(searchQuery).observe(this, Observer {
            it?.let {
                adapter.setData(it)
            }
        })
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}