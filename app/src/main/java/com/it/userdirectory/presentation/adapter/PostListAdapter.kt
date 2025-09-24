package com.it.userdirectory.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.it.userdirectory.R
import com.it.userdirectory.databinding.PostItemLayoutBinding
import com.it.userdirectory.databinding.UsersItemLayoutBinding
import com.it.userdirectory.domain.model.post.PostResponseItem
import com.it.userdirectory.domain.model.users.UsersResponseItem
import com.it.userdirectory.presentation.adapter.UserListAdapter.UserlistDiffCallback

class PostListAdapter(val context: Context): ListAdapter<PostResponseItem, PostListAdapter.MyViewHolder>(PostlistDiffCallback()) {
    private var lastPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding= PostItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val data=getItem(position)
        with(holder){
            binding.postTitle.text=data.title
            binding.postBody.text=data.body
        }
        val animation: Animation = android.view.animation.AnimationUtils.loadAnimation(
            context,
            if ((position > lastPosition))
                R.anim.up_from_bottom
            else
                R.anim.down_from_top
        )
        holder.itemView.startAnimation(animation)
        lastPosition = position
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
    inner  class MyViewHolder(val binding: PostItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    class PostlistDiffCallback : DiffUtil.ItemCallback<PostResponseItem>() {
        override fun areItemsTheSame(oldItem: PostResponseItem, newItem: PostResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostResponseItem, newItem: PostResponseItem): Boolean {
            return oldItem == newItem
        }
    }
}