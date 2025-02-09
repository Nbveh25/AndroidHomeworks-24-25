package com.example.homeworks.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cars",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "userId")
    val userId: Long,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
) 