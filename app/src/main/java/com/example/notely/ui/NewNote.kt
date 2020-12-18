package com.example.notely.ui

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notely.Data.models.Note
import com.example.notely.Data.viewmodel.NoteViewModel
import com.example.notely.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_new_note.*
import kotlinx.android.synthetic.main.fragment_new_note.view.*
import kotlinx.android.synthetic.main.layout_miscellaneous.view.*

class NewNote : Fragment() {
    private val mNoteViewModel: NoteViewModel by viewModels()
    private val mSharedViewModel : SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_note, container, false)
        setHasOptionsMenu(true)
        view.textDate.setText(mSharedViewModel.dateTime())
        miscellaneous(view)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add)
            insertDatatodb()
        return super.onOptionsItemSelected(item)
    }

    private fun insertDatatodb() {
        val mTitle = title.text.toString()
        val mSubtitle = subtitle.text.toString()
        val mDescription = description.text.toString()
        val mDateTime = textDate.text.toString()
        val mImagePath = "image"
        val mWebUrl = "www.google.com"
        val mColor = "background"
        val validation = mSharedViewModel.verifyData(mTitle, mDescription)
        if (validation) {
            //Insert Data to Database
            val newData = Note(
                0,
                mTitle,
                mSubtitle,
                mDescription,
                mDateTime,
                mImagePath,
                mWebUrl,
                mColor
            )
            mNoteViewModel.addNote(newData)
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_newNote_to_listNote)
        } else {
            Toast.makeText(context, "fill all the fields", Toast.LENGTH_SHORT).show()
        }


    }


    private fun miscellaneous(view: View){
        val bottomSheetBehavior :BottomSheetBehavior<LinearLayout> = BottomSheetBehavior.from(view.layoutMiscellaneous)
        view.layoutMiscellaneous.textMiscellaneous.setOnClickListener {
            if(bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            else
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

}