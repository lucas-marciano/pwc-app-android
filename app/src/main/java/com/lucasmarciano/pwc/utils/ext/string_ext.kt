package com.lucasmarciano.pwc.utils.ext

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Method is used for checking valid email id format.
 *
 * @return Boolean
 */
fun String.isEmailValid(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}