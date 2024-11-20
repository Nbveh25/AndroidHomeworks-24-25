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
            desc = "Рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),

        MusicHoldersData(
            id = 22,
            title = "Stop",
            singer = "Sam Brown",
            imageUrl = "https://avatars.yandex.net/get-music-content/49707/60c5255d.a.2515612-1/m1000x1000?webp=false",
            desc = "Stop! — песня британской певицы Сэм Браун. Выпущенный в 1988 году сингл занял 52 место в UK Singles Chart. Stop! стал на сегодняшний день самым большим хитом Браун, продержавшись в чартах 12 недель. В 1989 году песня стала тридцать пятым по счёту самым продаваемым синглом в Великобритании."
        ),

        MusicHoldersData(
            id = 23,
            title = "Bara Bara Bere Bere",
            singer = "Alex Ferrari",
            imageUrl = "https://content-18.foto.my.mail.ru/community/alex_ferrari/_musicplaylistcover/i-15.jpg",
            desc = "Бразильский певец Алекс Феррари зарегистрировал его в Music Edition: Kronika Records, записал в июне 2012 года и многократно интерпретировал в своей родной стране. Его версия завоевала наибольшую популярность на европейских ночных площадках. "
        ),

        MusicHoldersData(
            id = 24,
            title = "BABY SAID",
            singer = "Maneskin",
            imageUrl = "https://ih1.redbubble.net/image.3728114076.2767/raf,750x1000,075,t,FFFFFF:97ab1c12de.jpg",
            desc = "\"Baby Said\" (stylized in all caps as \"BABY SAID\") is a song by Italian rock band Måneskin. It was released on 3 March 2023 as the fifth single from their third studio album Rush! (2023)"
        ),

        MusicHoldersData(
            id = 25,
            title = "Hold On Love",
            singer = "Dan Balan",
            imageUrl = "https://avatars.yandex.net/get-music-content/3316841/4ee202cb.a.13487769-1/m1000x1000?webp=false",
            desc = "..."
        ),

        MusicHoldersData(
            id = 26,
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg",
            desc = "«Ooh Ahh» — сингл американской христианской хип-хоп группы GRITS при участии TobyMac для их четвёртого студийного альбома The Art of Translation. Продюсеры песни Рик «DJ Form» Роббинс и Отто Прайс из Incorporated Elements. Песня была написана Риком Роббинсом, Отто Прайсом, Стэйси Джонсом, Тероном Картером и TobyMac. Иногда песню называют «My Life Be Like» и «My Life Be Like (Ooh Ahh)»."
        ),

        MusicHoldersData(
            id = 27,
            title = "My Life Be Like",
            singer = "Grits, Toby Mac",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/0588c05d2a14348f682126819971b18e.jpg",
            desc = "me life be like ohh ahh"
        ),

        MusicHoldersData(
            id = 28,
            title = "Bloody Stream",
            singer = "Coda",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/27af20a01b0071ca19122aa478533b8c.jpg",
            desc = "Bloody Stream, также записывается, как BLOODY STREAM — дебютный сингл, исполненный японским музыкантом под псевдонимом Coda, членом музыкальной группы No Regret Life[англ.]. Автором текста является Саори Кодама, a композитором — Тосиюки Омори. Сингл использовался в качестве открывающей темы ко второй части первого сезона аниме-сериала JoJo’s Bizarre Adventure, точнее ко второй части - Battle Tendency"
        ),

        MusicHoldersData(
            id = 29,
            title = "Still loving you",
            singer = "Scorpions",
            imageUrl = "https://avatars.mds.yandex.net/get-entity_search/68218/98795321/S600xU",
            desc = "Рок-баллада группы Scorpions, выпущенная в альбоме Love at First Sting в 1984 году. Считается международной «визитной карточкой» группы. Во Франции было продано около 1,7 млн копий. Песня является одним из самых успешных синглов группы."
        ),
    )

    fun getRandomElem(): MultipleHoldersData {
        return items[(1..8).random()]
    }
}