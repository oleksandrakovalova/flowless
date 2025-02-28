package com.okproject.flowless.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.okproject.flowless.R
import com.okproject.flowless.mapper.toChipItem
import com.okproject.flowless.ui.component.chipgroup.ChipGroupSelector
import com.okproject.flowless.ui.component.colorpicker.ColorPicker
import com.okproject.flowless.ui.component.slider.SliderSelector
import com.okproject.flowless.ui.component.text.LabelText
import com.okproject.flowless.ui.component.text.TitleText
import com.okproject.flowless.ui.theme.Dimens
import com.okproject.flowless.ui.theme.FlowlessTheme

@Composable
fun BrushSettingsSheet(
    selectedBrushType: BrushType,
    brushTypes: List<BrushTypeChipItem>,
    onBrushTypeSelected: (BrushType) -> Unit,
    selectedBrushSize: Float,
    brushSizeRange: ClosedFloatingPointRange<Float>,
    onBrushSizeChanged: (Float) -> Unit,
    brushColor: Long,
    onBrushColorChanged: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TitleText(
            text = stringResource(R.string.brush_settings),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(Dimens.defaultPadding)
        )
        LabelText(
            text = stringResource(R.string.type)
        )
        ChipGroupSelector(
            selectedItem = selectedBrushType,
            items = brushTypes,
            onChipSelected = onBrushTypeSelected
        )
        LabelText(
            text = stringResource(R.string.size),
        )
        SliderSelector(
            position = selectedBrushSize,
            onPositionChangeFinished = onBrushSizeChanged,
            range = brushSizeRange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.defaultPadding)
        )
        LabelText(
            text = stringResource(R.string.color),
        )
        ColorPicker(
            selectedColor = Color(brushColor),
            onColorChanged = {
                onBrushColorChanged(it.value.toLong())
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun BrushSettingsSheetPreview() {
    FlowlessTheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            var selectedBrushType by remember { mutableStateOf(BrushType.PRESSURE_PEN) }
            var selectedBrushSize by remember { mutableFloatStateOf(5f) }
            var brushColor by remember { mutableLongStateOf(0xFFFFFFFF) }
            BrushSettingsSheet(
                selectedBrushType = selectedBrushType,
                brushTypes = BrushType.entries.map { it.toChipItem() },
                onBrushTypeSelected = {
                    selectedBrushType = it
                },
                selectedBrushSize = selectedBrushSize,
                brushSizeRange = 1f..25f,
                onBrushSizeChanged = {
                    selectedBrushSize = it
                },
                brushColor = brushColor,
                onBrushColorChanged = {
                    brushColor = it
                }
            )
        }
    }
}