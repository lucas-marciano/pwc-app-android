package com.lucasmarciano.pwc.view.questions

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasmarciano.pwc.R
import com.lucasmarciano.pwc.data.Question
import com.lucasmarciano.pwc.utils.Logger
import kotlinx.android.synthetic.main.item_question.view.*


class QuestionsAdapter : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {
    private val TAG = Logger.tag
    private lateinit var context: Context

    var questions: MutableList<Question> = mutableListOf()
    lateinit var response: () -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (Logger.DEBUG) Log.d(TAG, "onCreateViewHolder")
        context = parent.context

        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.item_question, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (Logger.DEBUG) Log.d(TAG, "onBindViewHolder")

        holder.bind(questions[position])
    }

    fun setResponseFunction(sendResponse: () -> Unit) {
        response = sendResponse
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(question: Question) {
            if (Logger.DEBUG) Log.d(TAG, "bind")

            itemView.tv_question.text = question.question
            itemView.tv_option_one.text = question.options[0]
            itemView.tv_option_two.text = question.options[1]
            itemView.tv_option_three.text = question.options[2]

            itemView.tv_option_one.setOnClickListener {
                response()

                it.setBackgroundColor(context.resources.getColor(R.color.colorButtonQuestion))
                itemView.tv_option_one.setTextColor(context.resources.getColor(R.color.colorPrimary))

                itemView.tv_option_two.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                itemView.tv_option_two.setTextColor(context.resources.getColor(R.color.colorTextPrimary))

                itemView.tv_option_three.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                itemView.tv_option_three.setTextColor(context.resources.getColor(R.color.colorTextPrimary))
            }

            itemView.tv_option_two.setOnClickListener {
                response()

                itemView.tv_option_one.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                itemView.tv_option_one.setTextColor(context.resources.getColor(R.color.colorTextPrimary))

                it.setBackgroundColor(context.resources.getColor(R.color.colorButtonQuestion))
                itemView.tv_option_two.setTextColor(context.resources.getColor(R.color.colorPrimary))

                itemView.tv_option_three.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                itemView.tv_option_three.setTextColor(context.resources.getColor(R.color.colorTextPrimary))
            }

            itemView.tv_option_three.setOnClickListener {
                response()

                itemView.tv_option_one.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                itemView.tv_option_one.setTextColor(context.resources.getColor(R.color.colorTextPrimary))

                itemView.tv_option_two.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
                itemView.tv_option_two.setTextColor(context.resources.getColor(R.color.colorTextPrimary))

                it.setBackgroundColor(context.resources.getColor(R.color.colorButtonQuestion))
                itemView.tv_option_three.setTextColor(context.resources.getColor(R.color.colorPrimary))
            }
        }
    }
}