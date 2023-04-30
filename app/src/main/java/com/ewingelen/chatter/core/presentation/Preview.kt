package com.ewingelen.chatter.core.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = UI_MODE_NIGHT_YES)
annotation class ElementPreview

@Preview(name = "Light theme, normal device")
@Preview(name = "Light theme, tablet landscape", device = Devices.TABLET)
@Preview(name = "Dark theme, small device", uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_5)
annotation class ScreenPreview
