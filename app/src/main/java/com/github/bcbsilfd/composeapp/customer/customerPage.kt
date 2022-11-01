package com.github.bcbsilfd.composeapp.customer

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bcbsilfd.composeapp.R
import com.github.bcbsilfd.composeapp.bottom_bar.BottomBar

@Composable
fun CustomerPage(activity: Activity) {
    val context = LocalContext.current

    Scaffold(
        topBar = { Toolbar(activity = activity) },
        bottomBar = { BottomBar() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Card(name = "Cards")
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Card(name = "Credits")
        }
    }
}

@Composable
fun Toolbar(activity: Activity) = TopAppBar(
    backgroundColor = Color.Transparent,
    elevation = 0.dp,
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
) {
    IconButton(onClick = { activity.onBackPressed() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            tint = Color.DarkGray,
            contentDescription = null
        )
    }
    Text(
        text = "Customer page",
        fontSize = 24.sp,
    )
}

@Composable
fun Card(name: String) {
    val context = LocalContext.current
    val density = LocalDensity.current

    var expandMoreOrLessIcon by remember { mutableStateOf(R.drawable.ic_baseline_expand_more_24) }
    var isSubItemVisible by remember { mutableStateOf(false) }

    @Composable
    fun getSubItemText() = Text(
        text = "This feature available for you, just add new " + name.dropLast(1),
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp),
        fontSize = 18.sp,
        fontFamily = FontFamily.Default
    )

    @Composable
    fun getSubItem() = Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        AnimatedVisibility(
            visible = isSubItemVisible,
            enter = slideInVertically { with(density) { -40.dp.roundToPx() } }
                    + expandVertically(expandFrom = Alignment.Top)
                    + fadeIn(initialAlpha = 0.1f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            getSubItemText()
        }
    }

    Column(
        modifier = Modifier
            .border(1.dp, Color(0x66B3B3B3), RoundedCornerShape(12.dp))
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Row(modifier = Modifier.padding(end = 16.dp)) {
            Text(
                text = name,
                fontWeight = FontWeight.Black,
                fontSize = 22.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            )
            IconButton(
                onClick = {
                    expandMoreOrLessIcon = if (expandMoreOrLessIcon == R.drawable.ic_baseline_expand_more_24) {
                        R.drawable.ic_baseline_expand_less_24
                    } else {
                        R.drawable.ic_baseline_expand_more_24
                    }
                    isSubItemVisible = !isSubItemVisible
                }
            ) {
                Icon(painter = painterResource(id = expandMoreOrLessIcon), contentDescription = null)
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { showToast(context, "Add new $name") },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_circle_outline_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .wrapContentWidth(Alignment.End),
                    tint = Color.Blue,
                )
            }
        }
        getSubItem()
    }

}

private fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}