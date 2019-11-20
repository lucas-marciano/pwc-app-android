package com.lucasmarciano.pwc.view.questions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasmarciano.pwc.R
import com.lucasmarciano.pwc.data.Question
import com.lucasmarciano.pwc.utils.Logger
import com.lucasmarciano.pwc.utils.ext.toast
import com.yarolegovich.discretescrollview.DSVOrientation
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.questions_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Questions screen.
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class QuestionsFragment : Fragment() {

    val TAG = Logger.tag
    private val viewModel: QuestionsViewModel by viewModel()
    private val adapter: QuestionsAdapter by inject()
    private var answered: MutableList<Int> = mutableListOf()
    private var currentQuestion: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.questions_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        adapter.questions = viewModel.loadFakeDate()
        adapter.setResponseFunction(::endingQuestions)

        question_list?.setOrientation(DSVOrientation.HORIZONTAL)
        question_list?.adapter = adapter
        question_list.setItemTransitionTimeMillis(100)
        question_list.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1f)
                .setMinScale(0.5f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build()
        )

        question_list.addOnItemChangedListener { _, index ->
            updateIndicator(index)

        }
    }

    /**
     * Method that update the indicator in the layout.
     *
     * @param index Int
     */
    private fun updateIndicator(index: Int) {
        if (Logger.DEBUG) Log.d(TAG, "updateIndicator")

        tv_current.text = "${index + 1}"
        currentQuestion = index

        when (index) {
            0 -> {
                v_item_one.background = resources.getDrawable(R.drawable.circle_bg_orange)
                v_item_two.background = resources.getDrawable(R.drawable.circle_bg_withe)
                v_item_trhee.background = resources.getDrawable(R.drawable.circle_bg_withe)
            }
            1 -> {
                v_item_one.background = resources.getDrawable(R.drawable.circle_bg_withe)
                v_item_two.background = resources.getDrawable(R.drawable.circle_bg_orange)
                v_item_trhee.background = resources.getDrawable(R.drawable.circle_bg_withe)
            }
            2 -> {
                v_item_one.background = resources.getDrawable(R.drawable.circle_bg_withe)
                v_item_two.background = resources.getDrawable(R.drawable.circle_bg_withe)
                v_item_trhee.background = resources.getDrawable(R.drawable.circle_bg_orange)
            }
            else -> {
                v_item_one.background = resources.getDrawable(R.drawable.circle_bg_orange)
                v_item_two.background = resources.getDrawable(R.drawable.circle_bg_withe)
                v_item_trhee.background = resources.getDrawable(R.drawable.circle_bg_withe)
            }
        }
    }

    /**
     *
     */
    private fun endingQuestions() {
        if (Logger.DEBUG) Log.d(TAG, "endingQuestions")

        if (!answered.contains(currentQuestion)) {
            answered.add(currentQuestion)
        }

        if (answered.size >= adapter.questions.size) {
            activity?.toast(R.string.message_end)
            view?.findNavController()?.navigate(R.id.action_questionsFragment_to_loginFragment)
        }
    }

}
