package com.example.notely.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notely.Data.models.Note
import com.example.notely.Data.viewmodel.NoteViewModel
import com.example.notely.R
import kotlinx.android.synthetic.main.fragment_update_note.*
import kotlinx.android.synthetic.main.fragment_update_note.view.*


class UpdateNote : Fragment() {
    //to get the file send by listNoteDirection
    private val args by navArgs<UpdateNoteArgs>()
    private val mNoteViewModel: NoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_note, container, false)

        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        view.update_title.setText(args.currentitem.title)
        view.update_subtitle.setText(args.currentitem.subtitle)
        view.update_description.setText(args.currentitem.description)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    //confirm removal
    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mNoteViewModel.deleteNote(args.currentitem)
            Toast.makeText(requireContext(), "Successfully Removed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNote_to_listNote)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentitem.title}'?")
        builder.setMessage("Are you sure you want to remove '${args.currentitem.title}'?")
        builder.create().show()
    }

    private fun updateItem() {
        val title = update_title.text.toString()
        val subtitle = update_subtitle.text.toString();
        val description = update_description.text.toString()
        val dateTime = "date"
        val imagePath = "image"
        val weburl ="url web"
        val color= "colornew"
        val updateItem = Note(
            args.currentitem.id,
            title,
            subtitle,
            description,
            dateTime,
            imagePath,
            weburl,
            color
        )
        mNoteViewModel.updateData(updateItem)
        Toast.makeText(requireContext(), "Successfully Updated!", Toast.LENGTH_SHORT).show()
        //navigate back to listNote
        findNavController().navigate(R.id.action_updateNote_to_listNote)
    }
}