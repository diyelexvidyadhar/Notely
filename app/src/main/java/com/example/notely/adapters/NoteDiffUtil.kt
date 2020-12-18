package com.example.notely.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.notely.Data.models.Note

class NoteDiffUtil(
    private val oldNoteList: List<Note>,
    private val newNoteList: List<Note>
): DiffUtil.Callback(){
    override fun getOldListSize() = oldNoteList.size


    override fun getNewListSize() = newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean{
        return oldNoteList[oldItemPosition] === newNoteList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
                && oldNoteList[oldItemPosition].title == newNoteList[newItemPosition].title
                && oldNoteList[oldItemPosition].subtitle == newNoteList[newItemPosition].subtitle
                && oldNoteList[oldItemPosition].description == newNoteList[newItemPosition].description
    }
}