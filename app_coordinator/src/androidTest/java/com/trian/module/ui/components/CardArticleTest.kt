package com.trian.module.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.trian.module.ui.components.cards.CardArticleRow


import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.domain.models.response.ArticleResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardArticleTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            TesMultiModuleTheme {
                CardArticleRow(article = ArticleResponse(
                    0,
                    "",
                    "",
                    "",
                    "ini title",
                    "",
                    "",
                    ""
                ), onClick = { article, index ->

                })
            }
        }
    }

    @Test
    fun shouldShowCard() {
        composeRule.onNodeWithText("ini title").assertExists()
    }
}