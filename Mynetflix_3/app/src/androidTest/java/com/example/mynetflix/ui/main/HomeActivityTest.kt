package com.example.mynetflix.ui.main


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynetflix.R
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
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
    private val helperMovie = DummyDataHelper.generateDataMovie()
    private val helperTvShow = DummyDataHelper.generateDataTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }


    @Test
    fun loadmovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                helperMovie.size
            )
        )
    }


    @Test
    fun loadtvshhow() {
        onView(withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                helperTvShow.size
            )
        )
    }
    

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
        onView(withId(R.id.movieDetail_name)).check(matches(withText(helperMovie[1].title)))

        onView(withId(R.id.movieDetail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_release)).check(matches(withText(helperMovie[1].releaseDate)))

        onView(withId(R.id.movieDetail_ratings)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_ratings)).check(matches(withText(helperMovie[1].movieRate)))

        for (i in 0..3) {
            onView(withId(R.id.movieDetail_ratings)).perform(ViewActions.swipeUp())
        }

        onView(withId(R.id.movieDetail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_desc)).check(matches(withText(helperMovie[1].description)))

        onView(withId(R.id.movieDetail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_genre)).check(matches(withText(helperMovie[1].genres)))

        onView(withId(R.id.movieDetail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_language)).check(matches(withText(helperMovie[1].originalLanguage)))
        for (i in 0..1) {
            onView(withId(R.id.movieDetail_language)).perform(ViewActions.swipeUp())
        }
        onView(withId(R.id.movieDetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_runtime)).check(matches(withText(helperMovie[1].runTime)))

        onView(withId(R.id.movieDetail_directors)).check(matches(isDisplayed()))
        onView(withId(R.id.movieDetail_directors)).check(matches(withText(helperMovie[1].filmDirector)))

    }


    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click())
        )
        onView(withId(R.id.image_poster_detail_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.tvShow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShow_title)).check(matches(withText(helperTvShow[1].title)))

        for (i in 0..2) {
            onView(withId(R.id.tvShow_title)).perform(ViewActions.swipeUp())
        }
        onView(withId(R.id.tvShowDetail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_release)).check(matches(withText(helperTvShow[1].releaseDate)))

        onView(withId(R.id.tvShow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShow_title)).check(matches(withText(helperTvShow[1].title)))

        for (i in 0..2) {
            onView(withId(R.id.tvShow_title)).perform(ViewActions.swipeUp())
        }

        onView(withId(R.id.tvShowDetail_ratings)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_ratings)).check(matches(withText(helperTvShow[1].ratings)))

        onView(withId(R.id.tvShowDetail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_desc)).check(matches(withText(helperTvShow[1].description)))

        for (i in 0..2) {
            onView(withId(R.id.tvShowDetail_desc)).perform(ViewActions.swipeUp())
        }
        onView(withId(R.id.tvShowDetail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_genre)).check(matches(withText(helperTvShow[1].tvShowGenre)))

        onView(withId(R.id.tvShowDetail_lang)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_lang)).check(matches(withText(helperTvShow[1].originalLanguage)))

        onView(withId(R.id.tvShowDetail_numepisodes)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_numepisodes)).check(matches(withText(helperTvShow[1].numOfEpisodes)))

        for (i in 0..2) {
            onView(withId(R.id.text_tvshow_numepisodes)).perform(ViewActions.swipeUp())
        }
        onView(withId(R.id.tvShowDetail_numseason)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_numseason)).check(matches(withText(helperTvShow[1].numOfSeasons)))

        onView(withId(R.id.tvShowDetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_runtime)).check(matches(withText(helperTvShow[1].runTimes)))

        onView(withId(R.id.tvShowDetail_creators)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowDetail_creators)).check(matches(withText(helperTvShow[1].creators)))

    }

}