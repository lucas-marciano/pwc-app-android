package com.lucasmarciano.pwc.utils

import com.lucasmarciano.pwc.BuildConfig


/**
 * Logger class util to help application logging.
 *
 * @project pwc
 * @create_at 2019-11-19
 * @author lucasmarciano
 */
object Logger {
    val DEBUG = BuildConfig.DEBUG
    private const val MAX_TAG_LENGTH = 23
    private const val CLASS_STACK_ITEM = 3

    /**
     * Returns A TAG for the caller class.
     *
     * @return The Tag to be used for logs.
     */
    val tag: String
        get() {
            val caller = Thread.currentThread().stackTrace[CLASS_STACK_ITEM]
            var tag = caller.className
            val lastDot = tag.lastIndexOf('.')

            if (lastDot > 0) tag = tag.substring(lastDot + 1)

            if (tag.length > MAX_TAG_LENGTH) tag = tag.substring(0, MAX_TAG_LENGTH)

            return tag
        }
}