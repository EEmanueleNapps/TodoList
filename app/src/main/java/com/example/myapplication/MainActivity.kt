package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var todos by rememberSaveable { mutableStateOf(emptyList<String>()) }

            var inputsTV by rememberSaveable { mutableStateOf("") }

            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = { // separa uma parte ao esqueleto (superior)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TextField(modifier = Modifier
                                .padding(10.dp),
                                value = inputsTV,
                                onValueChange = { newInputValue ->
                                    inputsTV = newInputValue
                                }
                            )
                            Button(modifier = Modifier
                                .padding(10.dp),
                                onClick = {
                                    val newTodos = todos.toMutableList()
                                    newTodos.add(inputsTV)
                                    todos = newTodos
                                    inputsTV = ""

                                },
                                content = {
                                    Text(text = "ADD")
                                }
                            )



                        }
                    }
                ) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(
                            items = todos
                        ) { todo ->
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp, 5.dp)
                                .clickable {
                                    todos = todos.toMutableList().apply { remove(todo) }
                                },
                                text = todo)

                        }
                    }


                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}