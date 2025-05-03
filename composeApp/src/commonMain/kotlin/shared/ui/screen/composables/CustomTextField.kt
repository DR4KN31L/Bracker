package shared.ui.screen.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import shared.utils.DARK_GREEN
import shared.utils.LIGHT_GREEN

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    painter: Painter? = null,
    interactionSource: MutableInteractionSource? = null,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityToggle: (() -> Unit)? = null
) {
    val usedInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

    val textFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = LIGHT_GREEN,
        focusedContainerColor = LIGHT_GREEN,
        focusedIndicatorColor = DARK_GREEN,
        unfocusedIndicatorColor = DARK_GREEN,
        focusedLabelColor = DARK_GREEN,
        unfocusedLabelColor = DARK_GREEN,
        unfocusedPlaceholderColor = DARK_GREEN,
        focusedPlaceholderColor = LIGHT_GREEN,
        cursorColor = DARK_GREEN
    )

    val visualTransformation = if (isPassword && !isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val trailingIcon: (@Composable (() -> Unit))? =
        if (isPassword && onVisibilityToggle != null) {
            {
                IconButton(onClick = onVisibilityToggle) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                    )
                }
            }
        } else null

    OutlinedTextField(
        value = value,
        interactionSource = usedInteractionSource,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        leadingIcon = if (painter != null) {
            {
                Icon(
                    painter = painter,
                    contentDescription = contentDescription,
                    tint = DARK_GREEN,
                    modifier = Modifier.size(24.dp)
                )
            }
        } else null,
        trailingIcon = trailingIcon,
        singleLine = true,
        visualTransformation = visualTransformation,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = textFieldColors
    )
}