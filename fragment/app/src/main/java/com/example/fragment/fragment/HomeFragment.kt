package com.example.fragment.fragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import android.system.Os.remove
import android.text.TextUtils.indexOf
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.R
import com.example.fragment.adapter.CustomAdapter
import com.example.fragment.callbacks.ItemSelectedCallback
import com.example.fragment.model.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var param5: String? = null
    private var param6: String? = null
    private var paramID: Boolean? = false

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomAdapter
    lateinit var dataContact: ArrayList<ItemViewModel>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        dataContact = ArrayList()
        recyclerView = view.findViewById(R.id.recyclerView)

        dataContact.add(ItemViewModel("Shubham", "8195064422", "#59, Manjit Nagar, Patiala"))

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CustomAdapter(requireActivity(), dataContact, itemSelectedCallback)
        recyclerView.adapter = adapter

        val addBtn = view.findViewById<FloatingActionButton>(R.id.addBtn)
        addBtn.setOnClickListener { addUser() }

        requireActivity().supportFragmentManager
            .setFragmentResultListener("requestKey", this) { _, bundle ->
                param1 = bundle.getString("bundleKey1")
                param2 = bundle.getString("bundleKey2")
                param3 = bundle.getString("bundleKey3")

                dataContact.add(ItemViewModel(param1!!, param2!!, param3!!))
                adapter.notifyDataSetChanged()
            }

        return view
    }

    @SuppressLint("NotifyDataSetChanged", "InflateParams")
    private fun addUser() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainLayout, AddPageFragment())
            .addToBackStack(null)
            .commit()
    }

    val itemSelectedCallback = object : ItemSelectedCallback {
        @SuppressLint("NotifyDataSetChanged")
        override fun onItemSelected(item: ItemViewModel, position:Int, mList: ArrayList<ItemViewModel>) {
            Log.d("TAG", "$item")

            /* To add hint in the edit fragment */
            requireActivity().supportFragmentManager
                .setFragmentResult(
                    "editKey",
                    bundleOf(
                        "nameHint" to item.userName,
                        "contactHint" to item.userMb,
                        "addressHint" to item.userDetails
                    )
                )

            /* Open Edit Fragment */
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainLayout, EditFragment())
                .addToBackStack(null)
                .commit()

            /* Get updated result from Edit Fragment */
            requireActivity().supportFragmentManager
                .setFragmentResultListener("requestKeyNew", viewLifecycleOwner) { _, bundle ->
                    paramID = bundle.getBoolean("bundleKeyBool")
                    param4 = bundle.getString("bundleKey1New")
                    param5 = bundle.getString("bundleKey2New")
                    param6 = bundle.getString("bundleKey3New")

                    if (paramID as Boolean) {              /* paramID true means Delete is pressed */
                        mList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    } else {
                        item.userName = "$param4"
                        item.userMb = "$param5"
                        item.userDetails = "$param6"
                    }

                    adapter.notifyDataSetChanged()
                }
        }
    }
}