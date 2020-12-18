package com.example.notely.ui

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.notely.Data.models.Note
import com.example.notely.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton,navigate:Boolean){
            view.setOnClickListener {
                if (navigate){
                    view.findNavController().navigate(R.id.action_listNote_to_newNote)
                }
            }
        }
////        @BindingAdapter("sendDataToUpdateFragment")
////        @JvmStatic
////        fun sendDataToUpdateFragment(view: LinearLayout,currentItem: Note){
////            view.setOnClickListener {
////                val action = ListNoteDirections.actionListNoteToUpdateNote(currentItem)
////                view.findNavController().navigate(action)
////            }
////        }
    }
}