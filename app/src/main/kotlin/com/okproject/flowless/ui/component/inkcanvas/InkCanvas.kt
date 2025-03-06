package com.okproject.flowless.ui.component.inkcanvas

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.widget.FrameLayout
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.ink.authoring.InProgressStrokeId
import androidx.ink.authoring.InProgressStrokesFinishedListener
import androidx.ink.authoring.InProgressStrokesView
import androidx.ink.rendering.android.canvas.CanvasStrokeRenderer
import com.okproject.flowless.InkBrush
import com.okproject.flowless.InkStroke

@SuppressLint("ClickableViewAccessibility")
@Composable
fun InkCanvas(
    brush: InkBrush,
    initialFinishedStrokes: Set<InkStroke>,
    onStrokeFinished: (Collection<InkStroke>) -> Unit,
    modifier: Modifier = Modifier
) {
    var finishedStrokes by remember { mutableStateOf(initialFinishedStrokes) }

    val canvasStrokeRenderer = CanvasStrokeRenderer.create()

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            factory = { context ->
                InProgressStrokesView(context).apply {
                    layoutParams =
                        FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                        )
                    addFinishedStrokesListener(
                        object : InProgressStrokesFinishedListener {
                            override fun onStrokesFinished(strokes: Map<InProgressStrokeId, InkStroke>) {
                                finishedStrokes += strokes.values
                                onStrokeFinished(strokes.values)
                                removeFinishedStrokes(strokes.keys)
                            }
                        }
                    )
                    setStrokeAuthoringTouchListenerWithBrush(brush)
                }
            },
            update = { view ->
                view.setStrokeAuthoringTouchListenerWithBrush(brush)
            }
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
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