package com.example.medreminder

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.medreminder.ui.AppStart
import com.example.medreminder.ui.theme.MedReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedReminderTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AppStart(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MedReminderTheme {

    }
}*/

