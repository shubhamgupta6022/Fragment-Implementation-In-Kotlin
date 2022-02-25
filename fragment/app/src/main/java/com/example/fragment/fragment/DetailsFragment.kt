package com.example.fragment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fragment.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private lateinit var detailName: TextView
    private lateinit var detailNo: TextView
    private lateinit var details:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("NAME")
            param2 = it.getString("PHONE")
            param3 = it.getString("DETAILS")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_details, container, false)

        detailName=v.findViewById(R.id.detailName)
        detailNo=v.findViewById(R.id.detailNo)
        details=v.findViewById(R.id.detailAddress)

        detailName.text=param1
        detailNo.text=param2
        details.text=param3

        return v
    }
}