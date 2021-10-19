package com.blogspot.svdevs.mynotes.feature_notes.presentation.notes.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun DefaultRadioButton(
    text:String,
    selected: Boolean,
    onSelected: () -> Unit, // triggered when a user checks a radio button
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
){

   Row(
       modifier = modifier,
       verticalAlignment = Alignment.CenterVertically
   ) {
       RadioButton(selected = selected,
           onClick = onSelected,
           colors = RadioButtonDefaults.colors(
               selectedColor = MaterialTheme.colors.primary,
               unselectedColor = MaterialTheme.colors.background
           )
       )
       Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
       Text(text = text, style = MaterialTheme.typography.body1)

   }

}