package com.trian.module.ui.components

import androidx.compose.ui.test.junit4.createComposeRule

import com.trian.module.ui.components.cards.CardHospital
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.domain.models.response.HospitalResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardHospitalResponseTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp(){
        composeRule.setContent {
            TesMultiModuleTheme {
                CardHospital(
                    hospitalResponse= HospitalResponse(
                        slug = "rs-tele-cexup",
                        description = "",
                        thumb = "",
                        thumb_original = "",
                        name = "RS Tele Cexup",
                        address = "Jl. Jakarta Barat RT005/003, Meruya",
                        others = "")
                ) { hospital, index -> }
            }
        }
    }

    @Test
    fun shouldShowAppBar(){

    }
}