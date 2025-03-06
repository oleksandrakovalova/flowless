package com.okproject.flowless.editor

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
import com.okproject.flowless.mapper.BrushMapper
import com.okproject.flowless.mapper.BrushTypeMapper
import com.okproject.flowless.mapper.StrokeMapper
import com.okproject.flowless.ui.component.inkcanvas.InkCanvas
import com.okproject.flowless.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    noteEditorViewModel: NoteEditorViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val brush by noteEditorViewModel.brush.collectAsStateWithLifecycle()
    val finishedStrokes by noteEditorViewModel.finishedStrokes.collectAsStateWithLifecycle()

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
                selectedBrushType = brush.brushType,
                brushTypes = BrushTypeMapper.mapToChipItems(noteEditorViewModel.brushTypes),
                onBrushTypeSelected = {
                    noteEditorViewModel.onBrushTypeSelected(it)
                },
                selectedBrushSize = brush.size,
                brushSizeRange = noteEditorViewModel.brushSizeRange,
                onBrushSizeChanged = {
                    noteEditorViewModel.onBrushSizeChanged(it)
                },
                brushColor = brush.color,
                onBrushColorChanged = {
                    noteEditorViewModel.onBrushColorChanged(it)
                }
            )
        },
    ) { innerPadding ->
        InkCanvas(
            brush = BrushMapper.mapToInkBrush(brush),
            initialFinishedStrokes = StrokeMapper.mapToInkStrokes(finishedStrokes),
            onStrokeFinished = {
                noteEditorViewModel.onStrokeFinished(StrokeMapper.mapToStrokes(it)) },
            Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        )
    }
}