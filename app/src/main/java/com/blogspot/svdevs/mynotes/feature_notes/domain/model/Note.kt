package com.blogspot.svdevs.mynotes.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blogspot.svdevs.mynotes.ui.theme.*
import java.lang.Exception

@Entity
data class Note(

    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null

){
    companion object{
        val noteColors = listOf(LightBlue, LightGreen, RedOrange, RedPink, BabyBlue, Violet)
    }
}

class InvalidNoteHandling(msg: String): Exception(msg)
