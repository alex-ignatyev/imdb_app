package com.sideki.imdb_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*setContent {
       Imdb_appTheme {
           Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colors.background
           ) {
               Greeting(viewModel.data)
           }
       }
   }
}*/
    }

/*@Composable
fun Greeting(title: String) {
    Text(text = "Фильм: $title")*/
}