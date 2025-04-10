package shared.ui.screen.HomeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import shared.viewModel.CategoryViewModel


@Composable
fun CategoryScreen(viewModel: CategoryViewModel, userId: Int, modifier: Modifier = Modifier, onContinue: () -> Unit) {

    val categories by viewModel.categories.collectAsState()
    val newCategory = remember { mutableStateOf("") }

    LaunchedEffect(Unit){
        viewModel.loadCategories(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenid@ a Bracker!")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            OutlinedTextField(
                modifier = Modifier.padding(24.dp),
                value = newCategory.value,
                onValueChange = { newCategory.value = it },
                label = { Text("Nueva Categoría!") }
            )

            ElevatedButton(
                onClick = {
                viewModel.upsertCategory(newCategory.value, userId)
                newCategory.value = ""
            }) {
                Text("+")
            }
        }

        customizedText(categories.isEmpty())

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ){
            LazyColumn(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryListItem(category.name)
                }
            }
        }
    }
}

@Composable
fun customizedText(bool: Boolean){
    if(bool) Text("Parece que no tienes categorias, Agreguemos una!") else Text("Tus categorias ;)")
}

@Composable
fun CategoryListItem(categoryName: String, onClick: () -> Unit = {}) {
    val shape = RoundedCornerShape(corner = CornerSize(16.dp))

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(width = 64.dp, height = 64.dp)
            .clip(shape)
            .clickable(
                onClick = onClick,
                indication = ripple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }
            ),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)    ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = categoryName,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "+",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
    }
}