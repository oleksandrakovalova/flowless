package com.okproject.flowless.ui.component.colorpicker

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.okproject.flowless.ui.theme.Dimens

val colorSliderModifier = Modifier
    .fillMaxWidth()
    .padding(
        horizontal = Dimens.largePadding,
        vertical = Dimens.defaultPadding
    )
    .height(Dimens.colorSliderHeight)