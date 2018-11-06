package edu.gatech.cs2340.myapplication;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

import edu.gatech.cs2340.myapplication.controllers.fragments.ViewLocationFragment;
import edu.gatech.cs2340.myapplication.models.TheCloud;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

/**
 * JUnit test for getLocations in TheCloud
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetLocationTest {

    /** this line is preferred way to hook up to activity */
    @Rule
    public ActivityTestRule<ViewLocationFragment> mActivityRule =
            new ActivityTestRule<>(TheCloud.class);

}
