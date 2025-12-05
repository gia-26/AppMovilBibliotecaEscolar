package com.example.appbibliotecaescolar

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appbibliotecaescolar.Vista.Login
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(Login::class.java)

    @Test
    fun verificarFlujoDeLogin() {
        onView(withId(R.id.edtUsuario)).check(matches(isDisplayed()))
        onView(withId(R.id.btnIngresar)).check(matches(isDisplayed()))

        onView(withId(R.id.edtUsuario))
            .perform(typeText("pedro.sanchez@alumno.uthh.edu.mx"), closeSoftKeyboard())

        onView(withId(R.id.edtPassword))
            .perform(typeText("pasS123$"), closeSoftKeyboard())

        onView(withId(R.id.btnIngresar)).perform(click())

        Thread.sleep(5000)
    }
}