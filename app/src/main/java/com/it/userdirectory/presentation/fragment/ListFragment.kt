package com.it.userdirectory.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.it.userdirectory.R
import com.it.userdirectory.databinding.FragmentListBinding
import com.it.userdirectory.presentation.adapter.UserListAdapter
import com.it.userdirectory.presentation.navigation.ScreenNavigation.Companion.detailScreen
import com.it.userdirectory.presentation.state.ListUiState
import com.it.userdirectory.presentation.viewmodel.UserViewModel
import com.it.userdirectory.utils.DataStore
import kotlinx.coroutines.launch


class ListFragment : Fragment() {
    private var binding: FragmentListBinding? = null
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var adapter: UserListAdapter
    lateinit var datastore: DataStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        datastore= DataStore(requireActivity())
        setupRecyclerView()


        getListData()

        binding!!.retrybtn.setOnClickListener {
            getListData()
        }
        binding!!.swipeRefreshLayout.setOnRefreshListener {
            getListData()
        }
        return binding!!.root
    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter(requireActivity()) { items ->
            lifecycleScope.launch {
                datastore.saveUser(items)
            }

            detailScreen(requireActivity())
        }
        binding!!.userlistview.layoutManager = LinearLayoutManager(requireActivity())
        binding!!.userlistview.adapter = adapter
    }

    private fun getListData() {
        viewModel.getUserList()
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is ListUiState.Loading -> {
                        if (!binding!!.swipeRefreshLayout.isRefreshing) {
                            binding!!.loadingbar.visibility = View.VISIBLE
                        }
                        binding!!.userlistview.visibility = View.INVISIBLE
                        binding!!.errorLayout.visibility = View.INVISIBLE
                    }
                    is ListUiState.Success -> {
                        binding!!.loadingbar.visibility = View.INVISIBLE
                        binding!!.swipeRefreshLayout.isRefreshing = false
                        binding!!.userlistview.visibility = View.VISIBLE
                        binding!!.errorLayout.visibility = View.INVISIBLE

                        adapter.submitList(state.data)
                    }
                    is ListUiState.Error -> {
                        binding!!.loadingbar.visibility = View.INVISIBLE
                        binding!!.swipeRefreshLayout.isRefreshing = false
                        binding!!.userlistview.visibility = View.INVISIBLE
                        binding!!.errorLayout.visibility = View.VISIBLE
                        binding!!.errortxt.text = state.message
                    }
                }
            }
        }
    }
}
