package com.okproject.flowless.data.storage

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.okproject.flowless.data.mapper.BrushTypeOptionMapper
import com.okproject.flowless.data.model.BrushOption
import com.okproject.flowless.domain.model.brush.Brush
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object BrushOptionSerializer: Serializer<BrushOption> {
    override val defaultValue: BrushOption = BrushOption(
        type = BrushTypeOptionMapper.mapToTypeOption(Brush.DEFAULT_BRUSH_TYPE),
        color = Brush.DEFAULT_BRUSH_COLOR,
        size = Brush.DEFAULT_BRUSH_SIZE,
        epsilon = Brush.DEFAULT_BRUSH_EPSILON
    )

    override suspend fun readFrom(input: InputStream): BrushOption {
        try {
            return BrushOption.ADAPTER.decode(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read BrushOption proto", exception)
        }
    }

    override suspend fun writeTo(t: BrushOption, output: OutputStream) =
        t.adapter.encode(output, t)

}