package com.github.bcbsilfd.composeapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bcbsilfd.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CustomerPage()
                }
            }
        }
    }

}

private fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

@Composable
fun Card(name: String) {
    var expandMoreOrLessIcon by remember {
        mutableStateOf(R.drawable.ic_baseline_expand_more_24)
    }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .shadow(2.dp, RoundedCornerShape(8.dp))
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Black,
            fontSize = 22.sp,
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 16.dp)
        )
        IconButton(
            onClick = {
                expandMoreOrLessIcon = if (expandMoreOrLessIcon == R.drawable.ic_baseline_expand_more_24) R.drawable.ic_baseline_expand_less_24
                else R.drawable.ic_baseline_expand_more_24
            }
        ) {
            Icon(painter = painterResource(id = expandMoreOrLessIcon), contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { showToast(context, "Add new $name")},
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_circle_outline_24),
                contentDescription = null,
                modifier = Modifier
                    .align(CenterVertically)
                    .wrapContentWidth(End),
                tint = Color.Blue,

            )
        }
    }
}

@Composable
fun CustomerPage() {
    Column {
        Card(name = "Cards")
        Card(name = "Credits")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        CustomerPage()
    }
}