package com.example.homeworks.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentQuestionBinding
import com.example.homeworks.repository.QuestionRepository
import com.google.android.material.card.MaterialCardView

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

        setupRadioButtons()

    }

    private fun setupRadioButtons() {
        var id = arguments?.getInt(ID)
        val questionModel = id.let { it?.let { it1 -> QuestionRepository.getQuestion(it1) } }
        val answers = questionModel?.answers
        with(viewBinding) {
            question.text = questionModel?.question

            answersGroup.removeAllViews()

            answers?.forEachIndexed { index, answer ->
                val radioButton = RadioButton(context).apply {
                    id = View.generateViewId()
                    text = answer.answer
                    textSize = 20f
                    setPadding(8,8,8,8)
                    layoutParams = ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(8,8,8,8)
                    }
                }

                val cardView = MaterialCardView(context).apply {
                    layoutParams = ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(8,16,8,16)
                    }
                    radius = 8f
                    setCardBackgroundColor(
                        resources.getColor(
                            com.google.android.material.R.color.design_default_color_background,
                            null
                        )
                    )
                    addView(radioButton)
                }

                radioButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        cardView.setCardBackgroundColor(
                            resources.getColor(R.color.colorSelected, null)
                        )
                    } else {
                        cardView.setCardBackgroundColor(
                            resources.getColor(
                                com.google.android.material.R.color.design_default_color_background,
                                null
                            )
                        )
                    }
                }

                answersGroup.addView(cardView)
            }
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