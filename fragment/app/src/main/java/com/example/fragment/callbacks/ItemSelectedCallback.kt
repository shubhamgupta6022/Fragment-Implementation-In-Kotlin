package com.example.fragment.callbacks

import com.example.fragment.model.ItemViewModel
import java.text.FieldPosition

interface ItemSelectedCallback {
    fun onItemSelected(item:ItemViewModel, position: Int, mList: ArrayList<ItemViewModel>)

}