package com.example.homeworks.repository

import android.content.Context
import com.example.homeworks.model.ButtonHoldersData
import com.example.homeworks.model.MultipleHoldersData
import com.example.homeworks.model.MusicHoldersData

object ScreenRepository {

    fun getListForMultipleTypes(ctx: Context): List<MultipleHoldersData> = listOf(

        ButtonHoldersData(
            id = "List",
            text = "List",
        ),

        ButtonHoldersData(
            id = "Grid",
            text = "Grid",
        ),

        ButtonHoldersData(
            id = "Optional",
            text = "Optional",
        ),

        MusicHoldersData(
            id = "music_1",
            title = "Still loving you",
            singer = "Scorpions",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/68218/98795321/S600xU"
        ),

        MusicHoldersData(
            id = "music_2",
            title = "Stop",
            singer = "Sam Brown",
            imageUrl = "https://avatars.yandex.net/get-music-content/49707/60c5255d.a.2515612-1/m1000x1000?webp=false"
        ),

        MusicHoldersData(
            id = "music_3",
            title = "Bara Bara Bere Bere",
            singer = "Alex Ferrari",
            imageUrl = "https://content-18.foto.my.mail.ru/community/alex_ferrari/_musicplaylistcover/i-15.jpg"
        ),

        MusicHoldersData(
            id = "music_4",
            title = "BABY SAID",
            singer = "Maneskin",
            imageUrl = "https://ih1.redbubble.net/image.3728114076.2767/raf,750x1000,075,t,FFFFFF:97ab1c12de.jpg"
        ),

        MusicHoldersData(
            id = "music_5",
            title = "Hold On Love",
            singer = "Dan Balan",
            imageUrl = "https://avatars.yandex.net/get-music-content/3316841/4ee202cb.a.13487769-1/m1000x1000?webp=false"
        ),

        MusicHoldersData(
            id = "music_6",
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg"
        ),

        MusicHoldersData(
            id = "music_7",
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg"
        ),

        MusicHoldersData(
            id = "music_8",
            title = "Bloody Stream",
            singer = "Coda",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/27af20a01b0071ca19122aa478533b8c.jpg"
        ),

        MusicHoldersData(
            id = "music_1",
            title = "Still loving you",
            singer = "Scorpions",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/68218/98795321/S600xU"
        ),

        MusicHoldersData(
            id = "music_2",
            title = "Stop",
            singer = "Sam Brown",
            imageUrl = "https://avatars.yandex.net/get-music-content/49707/60c5255d.a.2515612-1/m1000x1000?webp=false"
        ),

        MusicHoldersData(
            id = "music_3",
            title = "Bara Bara Bere Bere",
            singer = "Alex Ferrari",
            imageUrl = "https://content-18.foto.my.mail.ru/community/alex_ferrari/_musicplaylistcover/i-15.jpg"
        ),

        MusicHoldersData(
            id = "music_4",
            title = "BABY SAID",
            singer = "Maneskin",
            imageUrl = "https://ih1.redbubble.net/image.3728114076.2767/raf,750x1000,075,t,FFFFFF:97ab1c12de.jpg"
        ),

        MusicHoldersData(
            id = "music_5",
            title = "Hold On Love",
            singer = "Dan Balan",
            imageUrl = "https://avatars.yandex.net/get-music-content/3316841/4ee202cb.a.13487769-1/m1000x1000?webp=false"
        ),

        MusicHoldersData(
            id = "music_6",
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg"
        ),

        MusicHoldersData(
            id = "music_7",
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg"
        ),

        MusicHoldersData(
            id = "music_8",
            title = "Bloody Stream",
            singer = "Coda",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/27af20a01b0071ca19122aa478533b8c.jpg"
        ),

        MusicHoldersData(
            id = "music_1",
            title = "Still loving you",
            singer = "Scorpions",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/68218/98795321/S600xU"
        ),

        MusicHoldersData(
            id = "music_2",
            title = "Stop",
            singer = "Sam Brown",
            imageUrl = "https://avatars.yandex.net/get-music-content/49707/60c5255d.a.2515612-1/m1000x1000?webp=false"
        ),

        MusicHoldersData(
            id = "music_3",
            title = "Bara Bara Bere Bere",
            singer = "Alex Ferrari",
            imageUrl = "https://content-18.foto.my.mail.ru/community/alex_ferrari/_musicplaylistcover/i-15.jpg"
        ),

        MusicHoldersData(
            id = "music_4",
            title = "BABY SAID",
            singer = "Maneskin",
            imageUrl = "https://ih1.redbubble.net/image.3728114076.2767/raf,750x1000,075,t,FFFFFF:97ab1c12de.jpg"
        ),

        MusicHoldersData(
            id = "music_5",
            title = "Hold On Love",
            singer = "Dan Balan",
            imageUrl = "https://avatars.yandex.net/get-music-content/3316841/4ee202cb.a.13487769-1/m1000x1000?webp=false"
        ),

        MusicHoldersData(
            id = "music_6",
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg"
        ),

        MusicHoldersData(
            id = "music_7",
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg"
        ),

        MusicHoldersData(
            id = "music_8",
            title = "Bloody Stream",
            singer = "Coda",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/27af20a01b0071ca19122aa478533b8c.jpg"
        ),
    )

}