package com.lucasmarciano.pwc.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.lucasmarciano.pwc.R

/**
 * Main activity
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        return super.onCreateView(name, context, attrs)
    }
}