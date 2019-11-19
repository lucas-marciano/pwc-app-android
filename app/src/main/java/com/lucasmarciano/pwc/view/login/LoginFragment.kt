package com.lucasmarciano.pwc.view.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.viewmodel.ext.android.viewModel
import com.lucasmarciano.pwc.R
import com.lucasmarciano.pwc.utils.Logger

/**
 * Login screen.
 *
 * @project pwc
 * @create_at 2019-11-19
 * @author lucasmarciano
 */
class LoginFragment : Fragment() {

    val TAG = Logger.tag
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.login_fragment, container, false)
    }


}
