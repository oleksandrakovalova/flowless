package com.okproject.flowless.ext

import android.view.MotionEvent

fun MotionEvent.getPointerId(): Int =
    this.getPointerId(
        this.actionIndex
    )