package com.github.bcbsilfd.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.github.bcbsilfd.composeapp.customer.CustomerPage
import com.github.bcbsilfd.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CustomerPage(this)
                }
            }
        }
    }
}