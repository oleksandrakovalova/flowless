package com.okproject.flowless.mapper

import android.view.MotionEvent
import com.okproject.flowless.ui.component.inkcanvas.StrokeAction

fun MotionEvent.mapToStrokeAction(): StrokeAction =
    when(this.actionMasked) {
        MotionEvent.ACTION_DOWN -> StrokeAction.Start
        MotionEvent.ACTION_MOVE -> StrokeAction.Update
        MotionEvent.ACTION_UP -> StrokeAction.Finish
        MotionEvent.ACTION_CANCEL -> StrokeAction.Cancel
        else -> StrokeAction.Skip
    }
