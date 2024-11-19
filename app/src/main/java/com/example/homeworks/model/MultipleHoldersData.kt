package com.example.homeworks.model

sealed class MultipleHoldersData(
    open val id: Int,
)

class ButtonHoldersData(
    override val id: Int,
    val text: String
) : MultipleHoldersData(id)

class MusicHoldersData(
    override val id: Int,
    val title:String,
    val singer: String,
    val imageUrl: String,
    val desc: String
) : MultipleHoldersData(id)