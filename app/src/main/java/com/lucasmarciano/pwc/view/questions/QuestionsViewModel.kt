package com.lucasmarciano.pwc.view.questions

import androidx.lifecycle.ViewModel
import com.lucasmarciano.pwc.data.Question
import com.lucasmarciano.pwc.utils.Logger
import com.lucasmarciano.pwc.utils.prefs

/**
 * View model for the questions fragment
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class QuestionsViewModel : ViewModel() {
    val TAG = Logger.tag

    fun loadFakeDate(): MutableList<Question> {
        val questions: MutableList<Question> = mutableListOf()

        val question1 = "Sua cidade é ${prefs.cityName} ?"
        val question2 = "Seu estado é ${prefs.stateName} ?"
        val question3 = "Quantos filhos você tem ?"

        val options1: MutableList<String> = mutableListOf()
        options1.add("Sim")
        options1.add("Não")
        options1.add("Não sei afirmar")

        val options2: MutableList<String> = mutableListOf()
        options2.add("1 a 2")
        options2.add("3 a 4")
        options2.add("5 ou mais")

        questions.add(Question(question1, options1))
        questions.add(Question(question2, options1))
        questions.add(Question(question3, options2))

        return questions
    }
}
