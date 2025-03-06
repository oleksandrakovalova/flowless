package com.okproject.flowless.mapper

import androidx.ink.brush.InputToolType
import androidx.ink.strokes.MutableStrokeInputBatch
import com.okproject.flowless.InkStrokeInput
import com.okproject.flowless.InkStrokeInputBatch
import com.okproject.flowless.domain.model.stroke.StrokeInput
import com.okproject.flowless.domain.model.stroke.StrokeInputBatch
import com.okproject.flowless.domain.model.stroke.ToolType

object StrokeInputBatchMapper {
    private fun mapToToolType(inputType: InputToolType): ToolType =
        when(inputType) {
            InputToolType.STYLUS -> ToolType.STYLUS
            InputToolType.TOUCH -> ToolType.TOUCH
            InputToolType.MOUSE -> ToolType.MOUSE
            else -> ToolType.UNKNOWN
        }

    private fun mapToInputToolType(toolType: ToolType): InputToolType =
        when(toolType) {
            ToolType.STYLUS -> InputToolType.STYLUS
            ToolType.TOUCH -> InputToolType.TOUCH
            ToolType.MOUSE -> InputToolType.MOUSE
            ToolType.UNKNOWN -> InputToolType.UNKNOWN
        }

    fun mapToStrokeInputBatch(inputs: InkStrokeInputBatch): StrokeInputBatch {
        val mappedInputs = mutableListOf<StrokeInput>()
        val scratchInput = InkStrokeInput()

        for (i in 0 until inputs.size) {
            inputs.populate(i, scratchInput)
            mappedInputs.add(
                StrokeInput(
                    x = scratchInput.x,
                    y = scratchInput.y,
                    timeMillis = scratchInput.elapsedTimeMillis,
                    pressure = scratchInput.pressure,
                    tiltRadians = scratchInput.tiltRadians,
                    orientationRadians = scratchInput.orientationRadians,
                    strokeUnitLengthCm = scratchInput.strokeUnitLengthCm
                )
            )
        }

        return StrokeInputBatch(
            toolType = mapToToolType(inputs.getToolType()),
            strokeUnitLengthCm = inputs.getStrokeUnitLengthCm(),
            inputs = mappedInputs
        )
    }

    fun mapToInkStrokeInputBatch(inputs: StrokeInputBatch): InkStrokeInputBatch {
        val inputToolType = mapToInputToolType(inputs.toolType)
        val batch = MutableStrokeInputBatch()

        inputs.inputs.forEach { input ->
            batch.addOrThrow(
                type = inputToolType,
                x = input.x,
                y = input.y,
                elapsedTimeMillis = input.timeMillis,
                pressure = input.pressure,
                tiltRadians = input.tiltRadians,
                orientationRadians = input.orientationRadians
            )
        }

        return batch
    }
}