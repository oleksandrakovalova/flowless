package com.okproject.flowless.data.storage

import android.content.Context
import androidx.datastore.dataStore
import com.okproject.flowless.data.model.BrushOption
import com.okproject.flowless.data.model.BrushTypeOption
import kotlinx.coroutines.flow.Flow

class BrushStorageImpl(
    private val context: Context
): BrushStorage {
    override val storedBrushOption: Flow<BrushOption> = context.brushDataStore.data

    override suspend fun updateBrushColor(color: Long)  {
        context.brushDataStore.updateData { brushOption ->
            brushOption.copy(color = color)
        }
    }

    override suspend fun updateBrushSize(size: Float) {
        context.brushDataStore.updateData { brushOption ->
            brushOption.copy(size = size)
        }
    }

    override suspend fun updateBrushType(type: BrushTypeOption) {
        context.brushDataStore.updateData { brushOption ->
            brushOption.copy(type = type)
        }
    }

    companion object {
        private const val BRUSH_DATA_STORE_NAME = "brush_option.pb"
        private val Context.brushDataStore by dataStore(
            fileName = BRUSH_DATA_STORE_NAME,
            serializer = BrushOptionSerializer
        )
    }
}