package shared.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import bracker.composeapp.generated.resources.Res
import bracker.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

fun onClick(data: String) {
    println("Data From View: $data")
}
/*
* Hay que inyectar el viewmodel de manera adecuadaa con Koin
* y generar la correción de este view.
* */
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val maxChars = 15


    MaterialTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 400.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(128.dp)
                )
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        if(username.length < maxChars) username = it
                    },
                    label = { Text("Username") },
                    maxLines = 1,
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "Person Icon")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        if(password.length < maxChars) password = it
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    maxLines = 1,
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "Passwd Icon")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedButton(
                        onClick = { onClick(username) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Log in")
                    }
                    ElevatedButton(
                        onClick = { onClick(password) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Create Account")
                    }
                }
            }
        }
    }
}