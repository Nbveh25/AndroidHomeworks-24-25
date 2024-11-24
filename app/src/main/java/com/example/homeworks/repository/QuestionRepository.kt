package com.example.homeworks.repository

import com.example.homeworks.model.Answer
import com.example.homeworks.model.Question

object QuestionRepository {
    private val questions = listOf(
        Question(
            id = 1,
            question = "Какое из следующих событий произошло первым?",
            answers = listOf(
                Answer(id = 1, answer = "Первая мировая война"),
                Answer(id = 2, answer = "Открытие Америки Колумбом"),
                Answer(id = 3, answer = "Изобретение телефона")
            )
        ),
        Question(
            id = 2,
            question = "Какое значение имеет выражение 7 × (6 + 4) - 5?",
            answers = listOf(
                Answer(id = 1, answer = "65"),
                Answer(id = 2, answer = "70"),
                Answer(id = 3, answer = "75")
            )
        ),
        Question(
            id = 3,
            question = "Какой элемент периодической таблицы обозначается символом 'O'?",
            answers = listOf(
                Answer(id = 1, answer = "Золото"),
                Answer(id = 2, answer = "Кислород"),
                Answer(id = 3, answer = "Углерод")
            )
        ),
        Question(
            id = 4,
            question = "Кто является автором романа 'Война и мир'?",
            answers = listOf(
                Answer(id = 1, answer = "Фёдор Достоевский"),
                Answer(id = 2, answer = "Лев Толстой"),
                Answer(id = 3, answer = "Антон Чехов")
            )
        ),
        Question(
            id = 5,
            question = "Какая река является самой длинной в мире?",
            answers = listOf(
                Answer(id = 1, answer = "Нил"),
                Answer(id = 2, answer = "Амазонка"),
                Answer(id = 3, answer = "Миссисипи")
            )
        )
    )

    fun getQuestion(position: Int) : Question {
        return questions[position]
    }
}