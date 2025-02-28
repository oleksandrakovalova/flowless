package com.okproject.flowless.ui.component.chipgroup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.okproject.flowless.R
import com.okproject.flowless.ui.theme.Dimens
import com.okproject.flowless.ui.theme.FlowlessTheme

@Composable
fun <I: Any>ChipGroupSelector(
    selectedItem: I,
    items: List<ChipItem<I>>,
    onChipSelected: (I) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = Dimens.defaultPadding
        )
    ) {
        itemsIndexed(items, key = { _, item -> item.id }) { index, chipItem ->
            ElevatedFilterChip(
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 0.dp else Dimens.smallPadding,
                        end = if (index == items.lastIndex) 0.dp else Dimens.smallPadding
                    ),
                label = {
                    Text(text = stringResource(chipItem.labelRes))
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(chipItem.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.chipIconSize)
                    )
                },
                selected = chipItem.id == selectedItem,
                onClick = {
                    onChipSelected(chipItem.id)
                }
            )
        }
    }
}

private val previewChipItems = buildList {
    for (i in 1..5) {
        add(
            object : ChipItem<String> {
                override val id: String = "chip_$i"
                override val labelRes: Int = R.string.app_name
                override val iconRes: Int = R.drawable.ic_launcher_foreground

            }
        )
    }
}

@PreviewLightDark
@Composable
private fun ChipGroupSelectorPreview() {
    var selectedItem: String by remember { mutableStateOf(previewChipItems.first().id) }

    FlowlessTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            ChipGroupSelector(
                selectedItem = selectedItem,
                items = previewChipItems,
                onChipSelected = { selectedItem = it }
            )
        }
    }
}

