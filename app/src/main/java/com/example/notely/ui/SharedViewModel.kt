package com.example.notely.ui

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*


class SharedViewModel:ViewModel() {
     fun verifyData(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
            false
        else !(title.isEmpty() || description.isEmpty())
    }
     fun dateTime() : String {
        val date= SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.ENGLISH)
            .format(Date())
        return date.toString()
    }
}