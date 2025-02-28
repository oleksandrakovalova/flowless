package com.okproject.flowless.ui.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.okproject.flowless.ui.theme.Dimens

@Composable
fun LabelText(
    text: String,
    modifier: Modifier = defaultLabelModifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

private val defaultLabelModifier = Modifier.padding(
    start = Dimens.defaultPadding,
    end = Dimens.defaultPadding,
    top = Dimens.defaultPadding,
    bottom = Dimens.smallPadding
)