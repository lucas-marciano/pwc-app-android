package com.lucasmarciano.pwc.utils.ext

import android.app.Activity
import android.widget.Toast


fun Activity.toast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}