package com.trian.module.ui.components

import androidx.compose.ui.test.junit4.createComposeRule


import app.trian.driver.ui.theme.CexupTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardOrderResponseTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp(){
        composeRule.setContent {
            CexupTheme {

            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}