package com.github.bcbsilfd.composeapp.bottom_bar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.bcbsilfd.composeapp.R

@Composable
fun BottomBar() = BottomAppBar(
    backgroundColor = Color.Transparent,
    elevation = 0.dp,
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
) {
    val context = LocalContext.current

    fun getMenuMessage(icon: Int) = when (icon) {
        R.drawable.ic_baseline_home_24 -> "Home"
        R.drawable.ic_baseline_person_24 -> "Person"
        else -> "Settings"
    }


    @Composable
    fun getMenuItem(icon: Int) = IconButton(
        onClick = { showToast(context, getMenuMessage(icon)) }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Blue
        )
    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        getMenuItem(icon = R.drawable.ic_baseline_home_24)
        getMenuItem(icon = R.drawable.ic_baseline_person_24)
        getMenuItem(icon = R.drawable.ic_baseline_settings_24)
    }
}

private fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}