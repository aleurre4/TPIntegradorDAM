package com.jaime_urresti.tpintegrador;


import static android.support.test.espresso.Espresso.*;


import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import android.support.test.espresso.ViewAssertion;
import android.support.test.filters.LargeTest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void existeTextIdiomasCargados() {
                onView(withText("Idiomas cargados")).check((ViewAssertion) matches(isDisplayed()));

    }
}
