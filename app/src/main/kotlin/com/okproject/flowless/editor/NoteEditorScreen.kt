package com.okproject.flowless.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.okproject.flowless.mapper.toBrushFamily
import com.okproject.flowless.mapper.toChipItems
import com.okproject.flowless.ui.component.inkcanvas.InkCanvas
import com.okproject.flowless.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    noteEditorViewModel: NoteEditorViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val brushType by noteEditorViewModel.brushType.collectAsStateWithLifecycle()
    val brushSize by noteEditorViewModel.brushSize.collectAsStateWithLifecycle()
    val brushColor by noteEditorViewModel.brushColor.collectAsStateWithLifecycle()

    val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(
            topStart = Dimens.bottomSheetTopCornerRadius,
            topEnd = Dimens.bottomSheetTopCornerRadius
        ),
        sheetContent = {
            BrushSettingsSheet(
                modifier = Modifier.padding(vertical = Dimens.defaultPadding),
                selectedBrushType = brushType,
                brushTypes = noteEditorViewModel.brushTypes.toChipItems(),
                onBrushTypeSelected = {
                    noteEditorViewModel.onBrushTypeSelected(it)
                },
                selectedBrushSize = brushSize,
                brushSizeRange = noteEditorViewModel.brushSizeRange,
                onBrushSizeChanged = {
                    noteEditorViewModel.onBrushSizeChanged(it)
                },
                brushColor = brushColor,
                onBrushColorChanged = {
                    noteEditorViewModel.onBrushColorChanged(it)
                }
            )
        },
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            InkCanvas(
                brushFamily = brushType.toBrushFamily(),
                brushColor = brushColor,
                brushSize = brushSize,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}