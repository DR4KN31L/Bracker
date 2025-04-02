package shared.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Entity(
    tableName = "categories",
    foreignKeys = [
        ForeignKey(
            entity = User::class,  // DataClass que contiene el Foreign Key
            parentColumns = ["id"], // Key o primary column de USER
            childColumns = ["userId"], // la columna que contiene el ForeignKey
            onDelete = ForeignKey.CASCADE // Si se elimina un usuario, sus categorías también se eliminan
        )
    ],
    indices = [Index(value = ["userId"])] // Index para optimizar las consultas
)
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val items: Int,
    val userId: Int // Clave Foranea de User:Id
)