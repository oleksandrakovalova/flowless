package com.okproject.flowless.ui.component.slider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.okproject.flowless.ui.theme.FlowlessTheme

@Composable
fun SliderSelector(
    position: Float,
    onPositionChangeFinished: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float> = 0f..10f,
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableFloatStateOf(position) }
    Slider(
        modifier = modifier,
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        onValueChangeFinished = {
            onPositionChangeFinished(sliderPosition)
        },
        valueRange = range
    )
}

@Preview
@Composable
private fun SliderSelectorPreview() {
    FlowlessTheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SliderSelector(
                position = 0f,
                onPositionChangeFinished = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}