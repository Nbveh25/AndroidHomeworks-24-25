package com.example.homeworks.model

sealed class MultipleHoldersData(
    open val id: String,
)

class ButtonHoldersData(
    override val id: String,
    val text: String
) : MultipleHoldersData(id)

class MusicHoldersData(
    override val id: String,
    val title:String,
    val singer: String,
    val imageUrl: String
) : MultipleHoldersData(id)