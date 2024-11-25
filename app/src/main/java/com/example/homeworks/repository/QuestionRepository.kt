package com.example.homeworks.repository

import com.example.homeworks.model.Answer
import com.example.homeworks.model.Question

object QuestionRepository {
    private val questions = listOf(
        Question(
            id = 1,
            question = "Какой персонаж является первым носителем Стенда в 'Невероятных приключениях ДжоДжо'?",
            answers = listOf(
                Answer(id = 1, answer = "Джонатан Джостар"),
                Answer(id = 2, answer = "Дио Брандо"),
                Answer(id = 3, answer = "Джозеф Джостар")
            )
        ),
        Question(
            id = 2,
            question = "Как называется Стенд Джотаро Куджо?",
            answers = listOf(
                Answer(id = 1, answer = "Star Platinum"),
                Answer(id = 2, answer = "Gold Experience"),
                Answer(id = 3, answer = "Crazy Diamond")
            )
        ),
        Question(
            id = 3,
            question = "Какое событие стало началом конфликта между Джонатаном и Дио?",
            answers = listOf(
                Answer(id = 1, answer = "Смерть отца Джонатана"),
                Answer(id = 2, answer = "Появление Каменной маски"),
                Answer(id = 3, answer = "Поездка в Лондон")
            )
        ),
        Question(
            id = 4,
            question = "Кто является главным антагонистом в части 'Золотое ветвление'?",
            answers = listOf(
                Answer(id = 1, answer = "Дио Брандо"),
                Answer(id = 2, answer = "Пуччи"),
                Answer(id = 3, answer = "Ван Дер Рейк")
            )
        ),
        Question(
            id = 5,
            question = "Какой из этих персонажей не является членом семьи Джостар?",
            answers = listOf(
                Answer(id = 1, answer = "Джолин Куджо"),
                Answer(id = 2, answer = "Джорно Джованна"),
                Answer(id = 3, answer = "Рудольф Грэйс")
            )
        ),
        Question(
            id = 6,
            question = "Какой артефакт дает силу вампиру Дио?",
            answers = listOf(
                Answer(id = 1, answer = "Каменная маска"),
                Answer(id = 2, answer = "Золотое сердце"),
                Answer(id = 3, answer = "Кристалл времени")
            )
        ),
        Question(
            id = 7,
            question = "Какой стиль боя использует Джозеф Джостар?",
            answers = listOf(
                Answer(id = 1, answer = "Стиль 'Хамон'"),
                Answer(id = 2, answer = "Стиль 'Стэнд'"),
                Answer(id = 3, answer = "Стиль 'Кунг-фу'")
            )
        ),
        Question(
            id = 8,
            question = "Какой из этих Стендов является наиболее мощным по сравнению с другими?",
            answers = listOf(
                Answer(id = 1, answer = "The World"),
                Answer(id = 2, answer = "Silver Chariot"),
                Answer(id = 3, answer = "Hierophant Green")
            )
        ),
        Question(
            id = 9,
            question = "Кто является автором манги 'Невероятные приключения ДжоДжо'?",
            answers = listOf(
                Answer(id = 1, answer = "Хирохико Араки"),
                Answer(id = 2, answer = "Наоко Такеучи"),
                Answer(id = 3, answer = "Масаси Kishimoto")
            )
        ),
        Question(
            id = 10,
            question = "Какой из персонажей имеет способность управлять временем?",
            answers = listOf(
                Answer(id = 1, answer = "Дио Брандо"),
                Answer(id = 2, answer = "Пуччи"),
                Answer(id = 3, answer = "Джотаро Куджо")
            )
        )
    )

    fun getQuestion(position: Int): Question {
        return questions[position]
    }

    fun getSize() : Int = questions.size
}