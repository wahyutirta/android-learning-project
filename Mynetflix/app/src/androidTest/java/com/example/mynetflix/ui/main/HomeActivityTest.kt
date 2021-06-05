package com.example.mynetflix.ui.main


import android.app.Instrumentation
import android.os.SystemClock
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.mynetflix.R
import com.example.mynetflix.model.DummyDataHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/* custom swiper untuk testing scroll view
fun swiper(start: Int, end: Int, delay: Int) {
    val downTime = SystemClock.uptimeMillis()
    var eventTime = SystemClock.uptimeMillis()
    val inst: Instrumentation = getInstrumentation()
    var event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 500f, start.toFloat(), 0)
    inst.sendPointerSync(event)
    eventTime = SystemClock.uptimeMillis() + delay
    event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 500f, end.toFloat(), 0)
    inst.sendPointerSync(event)
    event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 500f, end.toFloat(), 0)
    inst.sendPointerSync(event)
    SystemClock.sleep(2000) //The wait is important to scroll
}
*/

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    private val testMovie = DummyDataHelper.generateDataMovie()
    private val testTvShow = DummyDataHelper.generateDataTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)


    /*
    test untuk memastikan fragment movie muncul dan bisa discrol
     */
    @Test
    fun loadmovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        testMovie.size
                )
        )
    }

    /*
        test untuk memastikan fragment tv show muncul dan bisa discrol
    */


    /*
            test untuk memastikan apakah detail movie terlihat seluruhnya, untuk itu disisipkan kode untuk melakukan swipe up
        */
    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        1,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.image_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_name)).check(matches(withText(testMovie[1].title)))

        onView(withId(R.id.movieDetail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_release)).check(matches(withText(testMovie[1].releaseDate)))

        onView(withId(R.id.movieDetail_ratings)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_ratings)).check(matches(withText(testMovie[1].movieRate)))

        for (i in 0..3) {
            onView(withId(R.id.movieDetail_ratings)).perform(swipeUp())
        }

        onView(withId(R.id.movieDetail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_desc)).check(matches(withText(testMovie[1].description)))

        onView(withId(R.id.movieDetail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_genre)).check(matches(withText(testMovie[1].genres)))

        onView(withId(R.id.movieDetail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_language)).check(matches(withText(testMovie[1].originalLanguage)))
        for (i in 0..1) {
            onView(withId(R.id.movieDetail_language)).perform(swipeUp())
        }
        onView(withId(R.id.movieDetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_runtime)).check(matches(withText(testMovie[1].runTime)))

        onView(withId(R.id.movieDetail_directors)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_directors)).check(matches(withText(testMovie[1].filmDirector)))

    }

    /*
                test untuk memastikan apakah detail tv show terlihat seluruhnya,
                untuk itu disisipkan kode untuk melakukan swipe up karena detail yang banyak
            */
    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click())
        )
        onView(withId(R.id.image_poster_detail_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.tvShow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShow_title)).check(matches(withText(testTvShow[1].title)))

        for (i in 0..2) {
            onView(withId(R.id.tvShow_title)).perform(swipeUp())
        }
        onView(withId(R.id.tvShowDetail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_release)).check(matches(withText(testTvShow[1].releaseDate)))

        onView(withId(R.id.tvShow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShow_title)).check(matches(withText(testTvShow[1].title)))

        for (i in 0..2) {
            onView(withId(R.id.tvShow_title)).perform(swipeUp())
        }

        onView(withId(R.id.tvShowDetail_ratings)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_ratings)).check(matches(withText(testTvShow[1].ratings)))

        onView(withId(R.id.tvShowDetail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_desc)).check(matches(withText(testTvShow[1].description)))

        for (i in 0..2) {
            onView(withId(R.id.tvShowDetail_desc)).perform(swipeUp())
        }
        onView(withId(R.id.tvShowDetail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_genre)).check(matches(withText(testTvShow[1].tvShowGenre)))

        onView(withId(R.id.tvShowDetail_lang)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_lang)).check(matches(withText(testTvShow[1].originalLanguage)))

        onView(withId(R.id.tvShowDetail_numepisodes)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_numepisodes)).check(matches(withText(testTvShow[1].numOfEpisodes)))

        for (i in 0..2) {
            onView(withId(R.id.text_tvshow_numepisodes)).perform(swipeUp())
        }
        onView(withId(R.id.tvShowDetail_numseason)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_numseason)).check(matches(withText(testTvShow[1].numOfSeasons)))

        onView(withId(R.id.tvShowDetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_runtime)).check(matches(withText(testTvShow[1].runTimes)))

        onView(withId(R.id.tvShowDetail_creators)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_creators)).check(matches(withText(testTvShow[1].creators)))

    }

}

