package shared.ui.screen.LoginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import bracker.composeapp.generated.resources.Res
import bracker.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import shared.viewModel.UserViewModel


@Composable
fun CreateAccountScreen(onNavigateBack : (String) -> Unit ,modifier: Modifier = Modifier) {

    val viewModel = koinViewModel<UserViewModel>()
    val name = viewModel.name.collectAsState().value
    val lastname = viewModel.lastName.collectAsState().value
    val username = viewModel.username.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var isPasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.snackbarMessages.collect { (message, color) ->
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                containerColor = Color.White,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        SnackbarHost(snackbarHostState) { data ->
                            Snackbar(
                                snackbarData = data,
                                modifier = Modifier
                                    .widthIn(min = 200.dp, max = 300.dp),
                                containerColor = Color.DarkGray,
                                contentColor = Color.White,
                            )
                        }
                    }

                    IconButton(
                        onClick = { onNavigateBack("") },
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = "Back To Login"
                        )
                    }

                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = modifier
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
                                value = name,
                                onValueChange = { viewModel.onNameChanged(it) },
                                label = { Text("Name") },
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                            )
                            OutlinedTextField(
                                value = lastname,
                                onValueChange = { viewModel.onLastNameChanged(it) },
                                label = { Text("Lastname") },
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                            )
                            OutlinedTextField(
                                value = username,
                                onValueChange = { viewModel.onUsernameChanged(it) },
                                label = { Text("Username") },
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                            )
                            OutlinedTextField(
                                value = password,
                                onValueChange = { viewModel.onPasswordChanged(it) },
                                label = { Text("Password") },
                                maxLines = 1,
                                trailingIcon = {
                                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
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
                                        viewModel.createUser { success ->
                                            if (success) onNavigateBack(username)
                                        }
                                    },
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
        }
    }
}