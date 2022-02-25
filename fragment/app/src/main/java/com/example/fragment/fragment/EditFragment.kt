package com.example.fragment.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.fragment.R

class EditFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var paramID: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_edit, container, false)

        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)
        val userAddress = v.findViewById<EditText>(R.id.userAddress)
        val saveBtn = v.findViewById<Button>(R.id.saveBtn)
        val deleteBtn = v.findViewById<Button>(R.id.deleteBtn)

        parentFragmentManager
            .setFragmentResultListener("editKey", this) { _, bundle ->
                // We use a String here, but any type that can be put in a Bundle is supported
                param1 = bundle.getString("nameHint")
                param2 = bundle.getString("contactHint")
                param3 = bundle.getString("addressHint")

                // Do something with the result
                userName.hint = "$param1"
                userNo.hint = "$param2"
                userAddress.hint = "$param3"
            }

        saveBtn.setOnClickListener {
            param1 = userName.text.toString()
            param2 = userNo.text.toString()
            param3 = userAddress.text.toString()

            requireActivity().supportFragmentManager.setFragmentResult(
                "requestKeyNew",
                bundleOf(
                    "bundleKeyBool" to paramID,
                    "bundleKey1New" to param1,
                    "bundleKey2New" to param2,
                    "bundleKey3New" to param3
                )
            )
//            val toast = Toast.makeText(this, "User update successful!", Toast.LENGTH_LONG)
//            toast.show()
        }

        deleteBtn.setOnClickListener {
            paramID = true
            requireActivity().supportFragmentManager.setFragmentResult(
                "requestKeyNew",
                bundleOf(
                    "bundleKeyBool" to paramID,
                    "bundleKey1New" to param1,
                    "bundleKey2New" to param2,
                    "bundleKey3New" to param3
                )
            )

//            val toast = Toast.makeText(this, "User Deleted!", Toast.LENGTH_LONG)
//            toast.show()
        }

        return v
    }
}
