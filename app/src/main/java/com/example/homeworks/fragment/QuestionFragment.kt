package com.example.homeworks.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworks.R
import com.example.homeworks.adapter.AnswerAdapter
import com.example.homeworks.databinding.FragmentQuestionBinding
import com.example.homeworks.repository.QuestionRepository

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private lateinit var viewBinding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentQuestionBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val id = arguments?.getInt(ID)
        val questionModel = id?.let { QuestionRepository.getQuestion(it) }
        val answers = questionModel?.answers ?: emptyList()

        with(viewBinding) {
            question.text = questionModel?.question

            answersRecyclerView.layoutManager = LinearLayoutManager(context)
            answersRecyclerView.adapter = AnswerAdapter(answers)
        }
    }

    companion object {
        private const val ID = "ID"

        fun getInstance(position: Int): QuestionFragment {
            return QuestionFragment().apply {
                arguments = bundleOf(
                    ID to position
                )
            }
        }
    }
}