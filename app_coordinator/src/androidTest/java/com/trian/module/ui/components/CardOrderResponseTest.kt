package com.trian.module.ui.components

import androidx.compose.ui.test.junit4.createComposeRule


import app.trian.user.ui.theme.BussTrackerTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardOrderResponseTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp(){
        composeRule.setContent {
            BussTrackerTheme {

            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}