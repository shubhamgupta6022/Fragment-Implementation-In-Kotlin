package com.example.fragment.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.R
import com.example.fragment.callbacks.ItemSelectedCallback
import com.example.fragment.fragment.EditFragment
import com.example.fragment.model.ItemViewModel

class CustomAdapter(
    val c: Activity,
    val mList: ArrayList<ItemViewModel>,
    val callBack: ItemSelectedCallback
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val fullName = v.findViewById<TextView>(R.id.fullName)
        val phoneNo = v.findViewById<TextView>(R.id.phoneNo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_row, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged", "InflateParams")
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.fullName.text = itemsViewModel.userName
        holder.phoneNo.text = itemsViewModel.userMb

        holder.itemView.setOnClickListener {
            callBack.onItemSelected(itemsViewModel, position, mList)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}