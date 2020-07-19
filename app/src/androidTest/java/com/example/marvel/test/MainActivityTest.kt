package com.example.marvel.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvel.MainActivity
import com.example.marvel.R
import com.example.marvel.fixture.Fixture
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun finish() {
        mockWebServer.shutdown()
    }

    @Test
    fun scrollList() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBodyDelay(500L, TimeUnit.MILLISECONDS)
            .setBody(Gson().toJson(Fixture.getCharactersResponse("response")))

        mockWebServer.enqueue(mockResponse)
        Thread.sleep(2000)

        onView(withId(R.id.rv_main))
            .perform(swipeUp())
    }
}