package com.trian.module.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.junit4.createComposeRule
import com.trian.module.ui.components.bottomsheet.BottomSheetServices
import com.trian.component.ui.theme.TesMultiModuleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomSheetServicesTest {
    @get:Rule
    val composeRule = createComposeRule()

    @ExperimentalFoundationApi
    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                BottomSheetServices(
                    onClick = {}
                )
            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}