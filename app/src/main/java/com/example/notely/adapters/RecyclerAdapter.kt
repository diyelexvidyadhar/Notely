package com.example.notely.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notely.Data.models.Note
import com.example.notely.R
import com.example.notely.ui.ListNoteDirections
import kotlinx.android.synthetic.main.fragment_new_note.view.*
import kotlinx.android.synthetic.main.item_container.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.NoteViewHolder>() {
    private var notes = emptyList<Note>()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.note_title.text = note.title
            itemView.note_subtitle.text = note.subtitle
            itemView.note_date.text = note.dateTime
            itemView.note_container.setOnClickListener {
                val action = ListNoteDirections.actionListNoteToUpdateNote(note)
                itemView.findNavController().navigate(action)
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    fun setData(mNote: List<Note>) {
        val noteDiffUtil = NoteDiffUtil(notes, mNote)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtil)
        this.notes = mNote
        noteDiffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false)
        )
    }
}