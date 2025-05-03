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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import bracker.composeapp.generated.resources.Res
import bracker.composeapp.generated.resources.compose_multiplatform
import bracker.composeapp.generated.resources.passwd
import bracker.composeapp.generated.resources.user
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import shared.data.model.LoggedInUser
import shared.ui.screen.composables.CustomTextField
import shared.ui.screen.composables.CustomizedButton
import shared.ui.screen.composables.TexturedBackground
import shared.utils.SOFT_GREEN
import shared.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    usernameParam: String?,
    modifier: Modifier = Modifier,
    onNavigateToCreateAccount: () -> Unit,
    onNavigateHome: (LoggedInUser) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val viewModel = koinViewModel<LoginViewModel>()
    val username = viewModel.username.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    val snackbarMessage = remember { mutableStateOf<Pair<String, Color>?>(null) }

    LaunchedEffect(usernameParam) {
        if (!usernameParam.isNullOrBlank()) {
            viewModel.setInitialUsername(usernameParam)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.snackbarMessages.collect { (message, color) ->
            snackbarMessage.value = message to color
            snackbarHostState.showSnackbar(message)
        }
    }
    TexturedBackground {
        MaterialTheme {
            Scaffold(
                containerColor = Color.Transparent,
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
                }
            ) { padding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding)
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
                            modifier = Modifier.size(128.dp)
                        )

                        CustomTextField(
                            value = username,
                            onValueChange = { viewModel.onUsernameChanged(it) },
                            label = "Username",
                            painter = painterResource(Res.drawable.user),
                        )

                        CustomTextField(
                            value = password,
                            onValueChange = { viewModel.onPasswordChanged(it) },
                            label = "Password",
                            painter = painterResource(Res.drawable.passwd),
                            isPassword = true,
                            isPasswordVisible = isPasswordVisible,
                            onVisibilityToggle = { isPasswordVisible = !isPasswordVisible },
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomizedButton(
                                onClick = {
                                    viewModel.logIn { success, loggedUser ->
                                        if (success && loggedUser != null) onNavigateHome(loggedUser)
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                text = "Log in",
                                color = SOFT_GREEN
                            )

                            CustomizedButton(
                                onClick = { onNavigateToCreateAccount() },
                                modifier = Modifier.weight(1f),
                                text = "Create Account",
                                color = SOFT_GREEN
                            )
                        }
                    }
                }
            }
        }
    }
}