package com.example.mynetflix.ui.main.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynetflix.R
import com.example.mynetflix.model.data.DummyDataHelper
import com.example.mynetflix.model.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuActivityTest {

    private val dataHelperMovie = DummyDataHelper.generateDataMovie()
    private val dataHelperTvShow = DummyDataHelper.generateDataTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MenuActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        Espresso.onView(ViewMatchers.withText("MOVIE")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataHelperMovie.size
            )
        )
    }


    @Test
    fun loadTvShow() {
        Espresso.onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataHelperTvShow.size
            )
        )
    }


    @Test
    fun loadDetailMovieAndTapFavorite() {
        Espresso.onView(ViewMatchers.withText("MOVIE")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        //love
        Espresso.onView(withId(R.id.love_btn)).perform(ViewActions.click())
        //unlove
        Espresso.onView(withId(R.id.love_btn)).perform(ViewActions.click())


        Espresso.onView(withId(R.id.image_poster_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.movieDetail_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_name))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].title)))

        Espresso.onView(withId(R.id.movieDetail_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_release))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].releaseDate)))

        Espresso.onView(withId(R.id.movieDetail_ratings))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_ratings))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].ratings)))

        for (i in 0..3) {
            Espresso.onView(withId(R.id.movieDetail_ratings)).perform(ViewActions.swipeUp())
        }

        Espresso.onView(withId(R.id.movieDetail_desc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_desc))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].description)))

        Espresso.onView(withId(R.id.movieDetail_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_genre))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].genres)))

        Espresso.onView(withId(R.id.movieDetail_language))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_language))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].originalLanguage)))

        for (i in 0..1) {
            Espresso.onView(withId(R.id.movieDetail_language)).perform(ViewActions.swipeUp())
        }

        Espresso.onView(withId(R.id.movieDetail_runtime))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_runtime))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].runTime)))

        Espresso.onView(withId(R.id.movieDetail_directors))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movieDetail_directors))
            .check(ViewAssertions.matches(withText(dataHelperMovie[0].filmDirector)))

    }


    @Test
    fun loadDetailTvShowAndTapFavorite() {
        Espresso.onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        //love
        Espresso.onView(withId(R.id.love_btn)).perform(ViewActions.click())
        //unlove
        Espresso.onView(withId(R.id.love_btn)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.image_poster_detail_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tvShow_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShow_title))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].title)))

        for (i in 0..2) {
            Espresso.onView(withId(R.id.tvShow_title)).perform(ViewActions.swipeUp())
        }

        Espresso.onView(withId(R.id.tvShowDetail_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_release))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].releaseDate)))

        Espresso.onView(withId(R.id.tvShow_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShow_title))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].title)))

        for (i in 0..2) {
            Espresso.onView(withId(R.id.tvShow_title)).perform(ViewActions.swipeUp())
        }

        Espresso.onView(withId(R.id.tvShowDetail_ratings))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_ratings))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].ratings)))

        Espresso.onView(withId(R.id.tvShowDetail_desc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_desc))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].description)))

        for (i in 0..2) {
            Espresso.onView(withId(R.id.tvShowDetail_desc)).perform(ViewActions.swipeUp())
        }

        Espresso.onView(withId(R.id.tvShowDetail_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_genre))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].genre)))

        Espresso.onView(withId(R.id.tvShowDetail_lang))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_lang))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].originalLanguage)))

        Espresso.onView(withId(R.id.tvShowDetail_numepisodes))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_numepisodes))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].numOfEpisodes)))

        for (i in 0..2) {
            Espresso.onView(withId(R.id.tvShowDetail_numepisodes)).perform(ViewActions.swipeUp())
        }

        Espresso.onView(withId(R.id.tvShowDetail_numseason))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_numseason))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].numOfSeasons)))

        Espresso.onView(withId(R.id.tvShowDetail_runtime))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_runtime))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].runTimes)))

        Espresso.onView(withId(R.id.tvShowDetail_creators))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvShowDetail_creators))
            .check(ViewAssertions.matches(withText(dataHelperTvShow[0].creators)))

    }

    @Test
    fun checkFavoriteListMovieTvShow() {
        Espresso.onView(withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("MOVIE")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            ViewActions.click()
        )

        Espresso.onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            ViewActions.click()
        )
    }

}