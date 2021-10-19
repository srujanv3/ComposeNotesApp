package com.blogspot.svdevs.mynotes.feature_notes.domain.utils

sealed class NoteOrderBy(val orderType: OrderType){

    class Title(orderType: OrderType): NoteOrderBy(orderType)
    class Date(orderType: OrderType): NoteOrderBy(orderType)
    class Color(orderType: OrderType): NoteOrderBy(orderType)

    //to keep the note order and change the ordertype
    fun copy(orderType: OrderType): NoteOrderBy{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }

}
