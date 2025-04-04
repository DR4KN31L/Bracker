package shared.ui.screen.LoginScreen

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import bracker.composeapp.generated.resources.Res
import bracker.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import shared.viewModel.LoginViewModel

@Composable
fun LoginScreen(usernameParam: String?,modifier: Modifier = Modifier,onNavigateToCreateAccount: () -> Unit,onNavigateHome:() -> Unit) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val viewModel = koinViewModel<LoginViewModel>()
    val username = viewModel.username.collectAsState().value
    val password = viewModel.password.collectAsState().value

    LaunchedEffect(usernameParam) {
        if(username.isNotEmpty()){ viewModel.setInitialUsername(usernameParam) }
    }

    println("[TEST] userAccount : $username")

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
                    onValueChange = { viewModel.onUsernameChanged(it) },
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
                    onValueChange = { viewModel.onPasswordChanged(it)},
                    label = { Text("Password") },
                    maxLines = 1,
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "Password Icon")
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }){
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                        onClick = {
                            viewModel.logIn { success ->
                                if (success) onNavigateHome()
                            }
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Log in")
                    }
                    ElevatedButton(
                        onClick = {onNavigateToCreateAccount()},
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