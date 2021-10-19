package com.blogspot.svdevs.mynotes.feature_notes.domain.utils

sealed class OrderType{

    object Ascending: OrderType()
    object Descending: OrderType()

}
