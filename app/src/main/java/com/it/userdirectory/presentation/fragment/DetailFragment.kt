package com.it.userdirectory.presentation.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.it.userdirectory.R
import com.it.userdirectory.databinding.FragmentDetailBinding
import com.it.userdirectory.presentation.adapter.PostListAdapter
import com.it.userdirectory.presentation.adapter.UserListAdapter
import com.it.userdirectory.presentation.state.DetailUiState
import com.it.userdirectory.presentation.viewmodel.PostViewModel
import com.it.userdirectory.presentation.viewmodel.UserViewModel
import com.it.userdirectory.utils.DataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.getValue


class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding?=null

    lateinit var datastore: DataStore
    private val viewModel: PostViewModel by activityViewModels()
    private lateinit var adapter: PostListAdapter
    var userId: Int?=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailBinding.inflate(inflater,container,false)
        datastore= DataStore(requireActivity())

       setUpRecyclerView()

        binding!!.retrybtn.setOnClickListener {
            getPostList(userId)
        }

        return binding!!.root
    }
    private fun getPostList(userId: Int?){
        viewModel.getPostList(userId)
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {state ->
                when (state) {
                    is DetailUiState.Loading -> {
                        binding!!.loadingbar.visibility= View.VISIBLE
                        binding!!.postsRecyclerView.visibility= View.INVISIBLE
                        binding!!.errorLayout.visibility= View.INVISIBLE

                    }
                    is DetailUiState.Success -> {
                        binding!!.loadingbar.visibility= View.INVISIBLE
                        binding!!.postsRecyclerView.visibility= View.VISIBLE
                        binding!!.errorLayout.visibility= View.INVISIBLE
                        adapter.submitList(state.data)
                    }
                    is DetailUiState.Error -> {
                        binding!!.loadingbar.visibility= View.INVISIBLE
                        binding!!.postsRecyclerView.visibility= View.INVISIBLE
                        binding!!.errorLayout.visibility= View.VISIBLE
                        binding!!.errortxt.text=state.message
                        Log.d("error","${state.message}")
                    }
                }

            }
        }

    }
    private fun setUpRecyclerView(){
        adapter= PostListAdapter(requireActivity())
        binding!!.postsRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding!!.postsRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewLifecycleOwner.lifecycleScope.launch {
            datastore.getUser.collect {userInfo->
                binding!!.fullName.text = userInfo?.username
                binding!!.username.text = userInfo?.username
                binding!!.email.text = userInfo?.email
                binding!!.phone.text = userInfo?.phone
                binding!!.website.text = userInfo?.website
                userId=userInfo?.id
                Log.d("myuserId","$userId")
                getPostList(userId)
            }
        }
    }
}