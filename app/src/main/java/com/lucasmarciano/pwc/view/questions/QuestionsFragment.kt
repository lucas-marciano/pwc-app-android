package com.lucasmarciano.pwc.view.questions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasmarciano.pwc.R
import com.lucasmarciano.pwc.utils.Logger
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.questions_fragment, container, false)
    }


}
