package com.lucasmarciano.pwc.view.questions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lucasmarciano.pwc.R

/**
 * Questions screen.
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class QuestionsFragment : Fragment() {

    private lateinit var viewModel: QuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.questions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
