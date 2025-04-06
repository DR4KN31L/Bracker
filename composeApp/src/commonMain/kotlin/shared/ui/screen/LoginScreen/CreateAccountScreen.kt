package shared.ui.screen.LoginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import bracker.composeapp.generated.resources.Res
import bracker.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import shared.viewModel.UserViewModel


@Composable
fun CreateAccountScreen(onNavigateBack: (String) -> Unit, modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<UserViewModel>()
    val name = viewModel.name.collectAsState().value
    val lastname = viewModel.lastName.collectAsState().value
    val username = viewModel.username.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    var isPasswordVisible by remember { mutableStateOf(false) }

    val snackbarMessage = remember { mutableStateOf<Pair<String, Color>?>(null) }


    LaunchedEffect(Unit) {
        viewModel.snackbarMessages.collect { (message, color) ->
            snackbarMessage.value = message to color
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = {

            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    containerColor = Color.DarkGray,
                    contentColor = snackbarMessage.value?.second ?: Color.White,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = data.visuals.message,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        topBar = {
            IconButton(
                onClick = { onNavigateBack("") },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "Back To Login"
                )
            }
        },
        containerColor = Color.White
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            val maxHeight = this.maxHeight

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = maxHeight)
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.height(16.dp))

                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(128.dp)
                )

                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .widthIn(min = 280.dp, max = 400.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = viewModel::onNameChanged,
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        value = lastname,
                        onValueChange = viewModel::onLastNameChanged,
                        label = { Text("Lastname") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        value = username,
                        onValueChange = viewModel::onUsernameChanged,
                        label = { Text("Username") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )

                    val passwordInteractionSource = remember { MutableInteractionSource() }
                    val isPasswordFocused by passwordInteractionSource.collectIsFocusedAsState()

                    OutlinedTextField(
                        value = password,
                        onValueChange = viewModel::onPasswordChanged,
                        label = { Text("Password") },
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
                            .padding(vertical = 4.dp),
                        interactionSource = passwordInteractionSource
                    )

                    if (isPasswordFocused) {
                        passwordCard()
                    }

                    Spacer(Modifier.height(8.dp))

                    ElevatedButton(
                        onClick = {
                            viewModel.createUser { success ->
                                if (success) onNavigateBack(viewModel.username.value)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Create Account")
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun passwordCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Requisitos de la Contraseña:", style = MaterialTheme.typography.bodyMedium)
            Text("• Al menos 8 caracteres", style = MaterialTheme.typography.bodySmall)
            Text("• Al menos una letra mayuscula", style = MaterialTheme.typography.bodySmall)
            Text("• Al menos un numero", style = MaterialTheme.typography.bodySmall)
            Text("• Al menos un caracter especial", style = MaterialTheme.typography.bodySmall)
        }
    }
}
