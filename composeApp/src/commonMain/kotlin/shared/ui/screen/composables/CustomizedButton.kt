package shared.ui.screen.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomizedButton(onClick: () -> Unit,modifier: Modifier,text: String, color: Color){
    ElevatedButton(
        onClick = onClick ,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = color,
            contentColor = Color.Black,
        )
    ){
        Text(text)
    }
}