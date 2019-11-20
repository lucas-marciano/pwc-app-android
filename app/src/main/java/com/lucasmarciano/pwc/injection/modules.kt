package com.lucasmarciano.pwc.injection

import com.lucasmarciano.pwc.view.login.LoginViewModel
import com.lucasmarciano.pwc.view.questions.QuestionsAdapter
import com.lucasmarciano.pwc.view.questions.QuestionsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin modules
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */

val viewsModules = module {
    viewModel { LoginViewModel(get()) }
    viewModel { QuestionsViewModel() }
}

val adaptersModules = module {
    factory { QuestionsAdapter() }
}