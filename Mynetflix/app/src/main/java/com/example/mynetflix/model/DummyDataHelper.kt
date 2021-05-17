package com.example.mynetflix.model

import com.example.mynetflix.R
import com.example.mynetflix.model.MovieModel
import com.example.mynetflix.model.TvShowModel

object DummyDataHelper {

    fun generateDataMovie(): ArrayList<MovieModel> {
        val movie = ArrayList<MovieModel>()

        movie.add(
            MovieModel(
                "M1",
                "Alita: Battle Angel",
                "14 February, 2019",
                "Rating: 72%· \u200E6,558 Ratings",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure",
                "English",
                "2 hr 2 min",
                R.drawable.poster_alita.toString(),
                "Robert Rodriguez"
            )
        )
        movie.add(
            MovieModel(
                "M2",
                "Aquaman",
                "21 December, 2018",
                "Rating: 69%· \u200E10,443 Ratings",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure, Fantasy",
                "English",
                "2h 23m",
                R.drawable.poster_aquaman.toString(),
                "James Wan"
            )
        )
        movie.add(
            MovieModel(
                "M3",
                "Bohemian Rhapsody",
                "23 October, 2018",
                "Rating: 80%· \u200E13,228 Ratings",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Music, Drama, History",
                "English",
                "2h 15m",
                R.drawable.poster_bohemian.toString(),
                "Anthony McCarten"
            )
        )
        movie.add(
            MovieModel(
                "M4",
                "Creed II",
                "21 November, 2018",
                "Rating: 69%· \u200E3,717 Ratings",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Drama",
                "English",
                "2h 10m",
                R.drawable.poster_creed.toString(),
                "Sylvester Stallone"
            )
        )
        movie.add(
            MovieModel(
                "M5",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "16 November, 2018",
                "Rating: 69%. \u200E8,097 Ratings",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Adventure, Fantasy, Drama",
                "English",
                "2h 14m",
                R.drawable.poster_creed.toString(),
                "Sylvester Stallone"
            )
        )





        return movie

    }

    fun generateDataTvShow(): List<TvShowModel> {
        val tvShow = ArrayList<TvShowModel>()

        tvShow.add(
            TvShowModel(
                "T1",
                "Game of Thrones",
                "April 17, 2011",
                "Rating: 9.3/10 · \u200E1,796,962 votes",
                "Game of Throne bisa dibilang sebagai tv series terbaik dalam satu dekade terakhir. Hal itu dirasa tidak berlebihan, karena tv series garapan David Bennioff dan D.B. Weiss sukses merebut perhatian jutaan orang di seluruh dunia. Terbukti serial tv ini sukses mendapatkan rating tertinggi di situs IMDB, yaitu 9,5.",
                "Serial, Epic, Tragedy, Action, Fantasy television, Action fiction, Drama, Suspense, Medieval fantasy, Thriller, Adventure, Special Interest",
                "Northern Ireland Dan Iceland",
                "73 Episode",
                "https://s4.bukalapak.com/uploads/content_attachment/9ef1d7fc30e8d7627da004c5/w-744/tv_series_terbaik_-1.jpg",
                "blaa",
                "lady gaga"
            )
        )



        return tvShow
    }

}