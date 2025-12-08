package com.example.feature_home

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.core_common.TestTags
import org.junit.Rule
import org.junit.Test

class HomeScreenTest
{
    @get:Rule
    val composeRule = createComposeRule()

   /* @Test
    fun homeScreen_displayLoadingState()
    {
        val vm = FakeHomeViewModel(HomeUiState(isloading = true))

        composeRule.setContent { HomeScreen(vm=vm,OnClickVideo = {})}

        composeRule.onNodeWithTag(TestTags.LOADING).assertIsDisplayed()
    }*/

    /*@Test
    fun homeScreen_displayLoadingState() {
        val vm = FakeHomeViewModel(HomeUiState(isloading = true))

        activityRule.scenario.onActivity { activity ->
            activity.setContent {
                HomeScreen(vm = vm, OnClickVideo = {})
            }
        }

        composeRule.onNodeWithTag(TestTags.LOADING).assertIsDisplayed()
    }*/

    @Test
    fun simpleComposeTest() {
        composeRule.setContent {
            Text("Hello")
        }

        composeRule.onNodeWithText("Hello").assertIsDisplayed()
    }
}