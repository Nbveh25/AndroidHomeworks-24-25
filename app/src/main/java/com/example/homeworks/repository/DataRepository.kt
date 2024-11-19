package com.example.homeworks.repository

import android.content.Context
import com.example.homeworks.model.MultipleHoldersData
import com.example.homeworks.model.MusicHoldersData

object DataRepository {

    private val items: MutableList<MultipleHoldersData> = mutableListOf(

        MusicHoldersData(
            id = 21,
            title = "Still loving you",
            singer = "Scorpions",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/68218/98795321/S600xU",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 22,
            title = "Stop",
            singer = "Sam Brown",
            imageUrl = "https://avatars.yandex.net/get-music-content/49707/60c5255d.a.2515612-1/m1000x1000?webp=false",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 23,
            title = "Bara Bara Bere Bere",
            singer = "Alex Ferrari",
            imageUrl = "https://content-18.foto.my.mail.ru/community/alex_ferrari/_musicplaylistcover/i-15.jpg",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 24,
            title = "BABY SAID",
            singer = "Maneskin",
            imageUrl = "https://ih1.redbubble.net/image.3728114076.2767/raf,750x1000,075,t,FFFFFF:97ab1c12de.jpg",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 25,
            title = "Hold On Love",
            singer = "Dan Balan",
            imageUrl = "https://avatars.yandex.net/get-music-content/3316841/4ee202cb.a.13487769-1/m1000x1000?webp=false",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 26,
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 27,
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 28,
            title = "Bloody Stream",
            singer = "Coda",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/27af20a01b0071ca19122aa478533b8c.jpg",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 29,
            title = "Still loving you",
            singer = "Scorpions",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/68218/98795321/S600xU",
            desc = "рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),
    )

    fun getListForMultipleTypes(ctx: Context): List<MultipleHoldersData> {
        return items
    }

    fun getRandomElem(): MultipleHoldersData {
        return items[(1..8).random()]
    }

    fun getSize() : Int = items.size

}