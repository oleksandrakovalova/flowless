package com.okproject.flowless.ui.component.colorpicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.okproject.flowless.ui.theme.FlowlessTheme

@Composable
fun ColorPicker(
    selectedColor: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    colorPickerController: ColorPickerController = rememberColorPickerController()
    ) {
    Column(
        modifier = modifier
    ) {
        HsvColorPicker(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = 0.dp,
                    horizontal = 32.dp
                ),
            controller = colorPickerController,
            initialColor = selectedColor,
            onColorChanged = { colorEnvelop ->
                onColorChanged(colorEnvelop.color)
            }
        )
        AlphaSlider(
            modifier = colorSliderModifier,
            controller = colorPickerController,
            initialColor = selectedColor
        )
        BrightnessSlider(
            modifier = colorSliderModifier,
            controller = colorPickerController,
            initialColor = selectedColor
        )
    }
}

@PreviewLightDark
@Composable
private fun ColorPickerPreview() {
    FlowlessTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            var color by remember { mutableStateOf(Color.Black) }
            ColorPicker(
                selectedColor = color,
                onColorChanged = { color = it }
            )
        }
    }
}