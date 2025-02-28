package com.okproject.flowless.ui.component.inkcanvas

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.widget.FrameLayout
import androidx.annotation.ColorLong
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.ink.authoring.InProgressStrokeId
import androidx.ink.authoring.InProgressStrokesFinishedListener
import androidx.ink.authoring.InProgressStrokesView
import androidx.ink.brush.Brush
import androidx.ink.brush.BrushFamily
import androidx.ink.rendering.android.canvas.CanvasStrokeRenderer
import androidx.ink.strokes.Stroke
import androidx.input.motionprediction.MotionEventPredictor

@SuppressLint("ClickableViewAccessibility")
@Composable
fun InkCanvas(
    brushFamily: BrushFamily,
    @ColorLong brushColor: Long,
    brushSize: Float,
    modifier: Modifier = Modifier
) {
    var finishedStrokes by remember { mutableStateOf(emptySet<Stroke>()) }
    val inProgressStrokesView: InProgressStrokesView = rememberInProgressStrokesView()
    val strokeAuthoringTouchListener = rememberStrokeAuthoringTouchListener(
        brushFamily = brushFamily,
        brushColor = brushColor,
        brushSize = brushSize,
        view = inProgressStrokesView
    )

    val canvasStrokeRenderer = CanvasStrokeRenderer.create()

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            factory = {
                inProgressStrokesView.apply {
                    layoutParams =
                        FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                        )
                    addFinishedStrokesListener(
                        object : InProgressStrokesFinishedListener {
                            override fun onStrokesFinished(strokes: Map<InProgressStrokeId, Stroke>) {
                                finishedStrokes += strokes.values
                                removeFinishedStrokes(strokes.keys)
                            }
                        }
                    )
                    setOnTouchListener(strokeAuthoringTouchListener)
                }
            },
            update = { view ->
                view.setOnTouchListener(strokeAuthoringTouchListener)
            }
        )
        Canvas(modifier = Modifier) {
            val canvasTransform = Matrix()
            drawContext.canvas.nativeCanvas.concat(canvasTransform)
            val canvas = drawContext.canvas.nativeCanvas

            finishedStrokes.forEach { stroke ->
                canvasStrokeRenderer.draw(
                    stroke = stroke,
                    canvas = canvas,
                    strokeToScreenTransform = canvasTransform
                )
            }
        }
    }
}

@Composable
fun rememberInProgressStrokesView(): InProgressStrokesView {
    val context = LocalContext.current
    return remember { InProgressStrokesView(context) }
}

@SuppressLint("ClickableViewAccessibility")
@Composable
fun rememberStrokeAuthoringTouchListener(
    brushFamily: BrushFamily,
    brushColor: Long,
    brushSize: Float,
    view: InProgressStrokesView
) = remember(brushFamily, brushColor, brushSize) {
    StrokeAuthoringTouchListener(
        brush = Brush.createWithColorLong(
            family = brushFamily,
            colorLong = brushColor,
            size = brushSize,
            epsilon = 0.1f
        ),
        motionEventPredictor = MotionEventPredictor.newInstance(view.rootView)
    )
}