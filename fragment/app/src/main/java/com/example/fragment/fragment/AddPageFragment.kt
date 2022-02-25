package com.example.fragment.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import com.example.fragment.R


class AddPageFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add_page, container, false)

        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)
        val userAddress = v.findViewById<EditText>(R.id.userAddress)
        val saveBtn = v.findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {
            param1 = userName.text.toString()
            param2 = userNo.text.toString()
            param3 = userAddress.text.toString()

            parentFragmentManager.setFragmentResult(
                "requestKey",
                bundleOf(
                    "bundleKey1" to param1,
                    "bundleKey2" to param2, "bundleKey3" to param3
                )
            )
        }
        return v
    }
}