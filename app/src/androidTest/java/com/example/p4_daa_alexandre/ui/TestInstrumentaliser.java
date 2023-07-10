package com.example.p4_daa_alexandre.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.p4_daa_alexandre.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestInstrumentaliser {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * rajouter le count pour savoir combien j'ai de meetings
     */

    /**
     * - un test d'affichage
     * - un test Add
     * - un test delete
     * - deux tests filter
     */

    /**
     * Supression d'une réunion
     */
    @Test
    public void deleteMeetingInList() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_meeting_recyclerview),
                                        0),
                                5),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    /**
     * Création d'une réunion
     */
    @Test
    public void creationMeetingTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.title_create_meeting_inputedittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Demo android"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.choose_time_button), withText("Open Time Picker"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.choose_date_button), withText("Open Date Picker"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.participant_create_meeting_inputedittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                5),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Alex, Antoine, David"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.room_name_create_meeting_inputedittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                6),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Metallica"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.create_button), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                7),
                        isDisplayed()));
        materialButton5.perform(click());
    }

    /**
     * filtre par jour seulement
     */
    @Test
    public void testInstrumentaliser2() {
        ViewInteraction frameLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.title_create_meeting_inputedittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Demo android"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.choose_time_button), withText("Open Time Picker"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.choose_date_button), withText("Open Date Picker"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                3),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.participant_create_meeting_inputedittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                5),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Thomas, David, Antoine"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.room_name_create_meeting_inputedittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                6),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Metallica"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.create_button), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_fragment),
                                        0),
                                7),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.filter_menu_item), withContentDescription("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Today"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.filter_menu_item), withContentDescription("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Today"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView2.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.filter_menu_item), withContentDescription("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Reset Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView3.perform(click());

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.filter_menu_item), withContentDescription("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction materialTextView4 = onView(
                allOf(withId(androidx.transition.R.id.title), withText("search room"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView4.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(androidx.appcompat.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.search_plate),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Demo android"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Collapse"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.filter_menu_item), withContentDescription("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()));
        actionMenuItemView5.perform(click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(androidx.transition.R.id.title), withText("search room"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView5.perform(click());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(androidx.appcompat.R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.search_plate),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("demo"), closeSoftKeyboard());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(androidx.appcompat.R.id.search_close_btn), withContentDescription("Clear query"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.search_plate),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.search_edit_frame),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());
    }

}
