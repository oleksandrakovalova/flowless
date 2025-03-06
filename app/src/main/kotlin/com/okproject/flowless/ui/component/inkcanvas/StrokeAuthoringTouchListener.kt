package com.okproject.flowless.ui.component.inkcanvas

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.ink.authoring.InProgressStrokeId
import androidx.ink.authoring.InProgressStrokesView
import androidx.input.motionprediction.MotionEventPredictor
import com.okproject.flowless.InkBrush
import com.okproject.flowless.ext.getPointerId
import com.okproject.flowless.mapper.mapToStrokeAction

class StrokeAuthoringTouchListener(
    private val brush: InkBrush,
    private val motionEventPredictor: MotionEventPredictor
): View.OnTouchListener {
    private var currentPointerId by mutableStateOf<Int?>(null)
    private var currentStrokeId by mutableStateOf<InProgressStrokeId?>(null)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (view !is InProgressStrokesView) return false
        val prediction = motionEventPredictor.run {
            record(event)
            predict()
        }

        return when(event.mapToStrokeAction()) {
            StrokeAction.Start -> {
                handleStrokeStart(
                    event = event,
                    view = view
                )
                true
            }
            StrokeAction.Update -> {
                handleStrokeUpdate(
                    event = event,
                    view = view,
                    prediction = prediction
                )
                true
            }
            StrokeAction.Finish -> {
                handleStrokeFinish(
                    event = event,
                    view = view
                )
                true
            }
            StrokeAction.Cancel -> {
                handleStrokeCancel(
                    event = event,
                    view = view
                )
                true
            }
            StrokeAction.Skip -> false
        }.also {
            doPostHandlerAction(event, view)
            prediction?.recycle()
        }
    }

    private fun handleStrokeStart(
        event: MotionEvent,
        view: InProgressStrokesView
    ) {
        view.requestUnbufferedDispatch(event)
        val pointerId = event.getPointerId()
        currentPointerId = pointerId
        currentStrokeId = view.startStroke(
            event = event,
            pointerId = pointerId,
            brush = brush
        )
    }

    private fun handleStrokeUpdate(
        event: MotionEvent,
        view: InProgressStrokesView,
        prediction: MotionEvent?
    ) {
        val pointerId = checkNotNull(currentPointerId)
        val strokeId = checkNotNull(currentStrokeId)

        for (pointerIndex in 0 until event.pointerCount) {
            if (event.getPointerId(pointerIndex) != pointerId) continue
            view.addToStroke(
                event,
                pointerId,
                strokeId,
                prediction,
            )
        }
    }

    private fun handleStrokeFinish(
        event: MotionEvent,
        view: InProgressStrokesView
    ) {
        val pointerId = event.getPointerId()
        check(pointerId == currentPointerId)
        val strokeId = checkNotNull(currentStrokeId)
        view.finishStroke(
            event,
            pointerId,
            strokeId
        )
    }

    private fun handleStrokeCancel(
        event: MotionEvent,
        view: InProgressStrokesView
    ) {
        val pointerId = event.getPointerId()
        check(pointerId == currentPointerId)

        val strokeId = checkNotNull(currentStrokeId)
        view.cancelStroke(strokeId, event)
    }

    private fun doPostHandlerAction(event: MotionEvent, view: View) {
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            view.performClick()
        }
    }
}

fun View.setStrokeAuthoringTouchListenerWithBrush(brush: InkBrush) {
    val motionEventPredictor = MotionEventPredictor.newInstance(this)
    this.setOnTouchListener(
        StrokeAuthoringTouchListener(
            brush = brush,
            motionEventPredictor = motionEventPredictor
        )
    )
}