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
import com.it.userdirectory.domain.model.user.User
import com.it.userdirectory.databinding.UsersItemLayoutBinding

class UserListAdapter(val context: Context,val onclick:(User)->Unit): ListAdapter<User, UserListAdapter.MyViewHolder>(UserlistDiffCallback())  {

    private var lastPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding= UsersItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {

          val data=getItem(position)
        with(holder){
            binding.tvUserName.text=data.username
            binding.tvUserEmail.text=data.email
            binding.tvUserPhone.text=data.phone
            itemView.setOnClickListener {
                onclick(data)
            }
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
    override fun onViewDetachedFromWindow(holder: UserListAdapter.MyViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    inner  class MyViewHolder(val binding: UsersItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    class UserlistDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}