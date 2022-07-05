package com.trian.module.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.junit4.createComposeRule
import com.trian.module.ui.components.bottomsheet.BottomSheetDevices
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomSheetDevicesModelTest {
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalMaterialApi
    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                BottomSheetDevices(
                    device = listOf(),
                    onDeviceSelected = {}
                )
            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}