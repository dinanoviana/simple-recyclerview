package com.whitestudio.sidejo.util

import android.view.View

fun View.hide(isGone: Boolean = true) {
    if (visibility == View.VISIBLE) {
        visibility = if (isGone) {
            View.GONE
        } else {
            View.INVISIBLE
        }
    }
}

fun View.show() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}