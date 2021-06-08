package com.example.mynetflix.model.data

import com.example.mynetflix.model.data.source.remote.response.MovieResponse
import com.example.mynetflix.model.data.source.remote.response.TvShowResponse

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
                "https://m.media-amazon.com/images/M/MV5BMTQzYWYwYjctY2JhZS00NTYzLTllM2UtZWY5ZTk0NmYwYzIyXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg",
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
                "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_.jpg",
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
                "https://m.media-amazon.com/images/M/MV5BMTA2NDc3Njg5NDVeQTJeQWpwZ15BbWU4MDc1NDcxNTUz._V1_.jpg",
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
                "https://m.media-amazon.com/images/M/MV5BYmEyNWZhM2YtZDAyNi00ZjYzLWI2ZWMtOWM4ZmI1MDE0OWNmXkEyXkFqcGdeQXVyMjMwNDgzNjc@._V1_.jpg",
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
                "https://m.media-amazon.com/images/M/MV5BYWVlMDI5N2UtZTIyMC00NjZkLWI5Y2QtODM5NGE5MzA0NmVjXkEyXkFqcGdeQXVyNzU3NjUxMzE@._V1_FMjpg_UX1000_.jpg",
                "David Yates"
            )
        )
        movie.add(
            MovieModel(
                "M6",
                "How to Train Your Dragon: The Hidden World",
                "09 January, 2019",
                "Rating: 78%. \u200E4,535 Ratings",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "English",
                "1h 44m",
                "poster_how_to_train",
                "Dean DeBlois"
            )
        )
        movie.add(
            MovieModel(
                "M6",
                "How to Train Your Dragon: The Hidden World",
                "09 January, 2019",
                "Rating: 78%. \u200E4,535 Ratings",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "English",
                "1h 44m",
                "https://m.media-amazon.com/images/M/MV5BMjIwMDIwNjAyOF5BMl5BanBnXkFtZTgwNDE1MDc2NTM@._V1_.jpg",
                "Dean DeBlois"
            )
        )
        movie.add(
            MovieModel(
                "M7",
                "Avengers: Infinity War",
                "27 April, 2018",
                "Rating: 83%. \u200E21,772 Ratings",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Adventure, Action, Science Fiction",
                "English",
                "2h 29m",
                "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_.jpg",
                "Joe Russo, Anthony Russo"
            )
        )
        movie.add(
            MovieModel(
                "M8",
                "Mary Queen of Scots",
                "21 December, 2018",
                "Rating: 66%. \u200E1,339 Ratings",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                "Drama, History",
                "English",
                "2h 4m",
                "https://m.media-amazon.com/images/M/MV5BNDVmOGI4MTMtYmNmNC00MTliLTlkYjQtYmU2N2EyNDk2YTAwXkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_.jpg",
                "Josie Rourke"
            )
        )
        movie.add(
            MovieModel(
                "M9",
                "Master Z: Ip Man Legacy",
                "26 December, 2018",
                "Rating: 66%. \u200E327 Ratings",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "Actions",
                "Cantonese",
                "1h 47m",
                "https://m.media-amazon.com/images/M/MV5BMTYxNzA0ODQyMF5BMl5BanBnXkFtZTgwNzUwNTg1NzM@._V1_.jpg",
                "Yuen Woo-ping"
            )
        )
        movie.add(
            MovieModel(
                "M10",
                "Mortal Engines",
                "14 December, 2018",
                "Rating: 61%. \u200E3,371 Ratings",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "Adventure, Science Fiction",
                "English",
                "2h 9m",
                "https://m.media-amazon.com/images/M/MV5BNzY1MDA2OTQ0N15BMl5BanBnXkFtZTgwMTkzNjU2NTM@._V1_.jpg",
                "Christian Rivers"
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
                "Rating: 9.3/10 · \u200E1,808,911 votes",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Serial, Epic, Tragedy, Action, Fantasy television, Action fiction, Drama, Suspense, Medieval fantasy, Thriller, Adventure, Special Interest",
                "English",
                "73 Episode",
                "8 Seasons",
                "57 min",
                "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg",
                "David Benioff, D.B. Weiss"
            )
        )
        tvShow.add(
            TvShowModel(
                "T2",
                "Doom Patrol",
                "15 February, 2019",
                "Rating: 76%. \u200E827 Ratings",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "English",
                "24 Episodes",
                "2 Seasons",
                "60 min",
                "https://m.media-amazon.com/images/M/MV5BM2I1OWZiNTUtZGNmOS00NGNiLTlhZmItOTU3MGRlMWFkYWU3XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg",
                "Jeremy Carver"
            )
        )
        tvShow.add(
            TvShowModel(
                "T3",
                "Arrow",
                "10 October, 2012",
                "Rating: 66%. \u200E4,536 Ratings",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Crime, Drama, Mystery, Action & Adventure",
                "English",
                "170 Episodes",
                "8 Seasons",
                "42 min",
                "https://m.media-amazon.com/images/M/MV5BMTI0NTMwMDgtYTMzZC00YmJhLTg4NzMtMTc1NjI4MWY4NmQ4XkEyXkFqcGdeQXVyNTY3MTYzOTA@._V1_.jpg",
                "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg"
            )
        )
        tvShow.add(
            TvShowModel(
                "T4",
                "Family Guy",
                "31 January, 1999",
                "Rating: 70%. \u200E2,763 Ratings",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Animation, Comedy",
                "English",
                "369 Episodes",
                "19 Seasons",
                "22 min",
                "https://m.media-amazon.com/images/M/MV5BODEwZjEzMjAtNjQxMy00Yjc4LWFlMDAtYjhjZTAxNDU3OTg3XkEyXkFqcGdeQXVyOTM2NTM4MjA@._V1_.jpg",
                "Seth MacFarlane"
            )
        )
        tvShow.add(
            TvShowModel(
                "T5",
                "Flash",
                "7 October, 2014",
                "Rating: 66%. \u200E4,536 Ratings",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Drama, Sci-Fi & Fantasy",
                "English",
                "142 Episodes",
                "7 Seasons",
                "44 min",
                "https://m.media-amazon.com/images/M/MV5BMDIzNzYwNTctZWY4Mi00YjQ2LWI5YWYtMzdmNDgwMGI4Yzk1XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg",
                "Greg Berlanti, Geoff Johns, Andrew Kreisberg"
            )
        )
        tvShow.add(
            TvShowModel(
                "T6",
                "Gotham",
                "22 September, 2014",
                "Rating: 75%. \u200E2,296 Ratings",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Drama, Crime, Sci-Fi & Fantasy",
                "English",
                "100 Episodes",
                "5 Seasons",
                "43 min",
                "https://m.media-amazon.com/images/M/MV5BMTU5NjQ2MTU4NV5BMl5BanBnXkFtZTgwOTYyNTAwNzM@._V1_.jpg",
                "Bruno Heller"
            )
        )
        tvShow.add(
            TvShowModel(
                "T7",
                "Marvel's Iron Fist",
                "17 March, 2017",
                "Rating: 66%. \u200E 1,604 Ratings",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "English",
                "23 Episodes",
                "2 Seasons",
                "55 min",
                "https://m.media-amazon.com/images/M/MV5BMjI5Mjg1NDcyOV5BMl5BanBnXkFtZTgwMjAxOTQ5MTI@._V1_.jpg",
                "Scott Buck"
            )
        )
        tvShow.add(
            TvShowModel(
                "T8",
                "Supergirl",
                "26 October, 2015",
                "Rating: 73%. \u200E3,060 Ratings",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Drama, Sci-Fi & Fantasy, Action & Adventure",
                "English",
                "113 Episodes",
                "6 Seasons",
                "42 min",
                "https://m.media-amazon.com/images/M/MV5BODQ3MmFkZjQtZDVlYi00NTA3LWE2YTMtZmM5NzNkZGE5ZTdjXkEyXkFqcGdeQXVyMTA1OTAyOTI@._V1_.jpg",
                "Greg Berlanti, Ali Adler, Andrew Kreisberg"
            )
        )
        tvShow.add(
            TvShowModel(
                "T9",
                "Riverdale",
                "26 January, 2017",
                "Rating: %. \u200E Ratings",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Mystery, Drama, Crime",
                "English",
                "86 Episodes",
                "5 Seasons",
                "45 min",
                "https://m.media-amazon.com/images/M/MV5BZTg3OTczYmMtMWIxNi00NWIzLTg3ZjAtZjRkMTNkNGQ5Y2E0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg",
                "Roberto Aguirre-Sacasa"
            )
        )
        tvShow.add(
            TvShowModel(
                "T10",
                "The Walking Dead",
                "31 October, 2010",
                "Rating: %. \u200E Ratings",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Action & Adventure, Drama, Sci-Fi & Fantasy\n",
                "English",
                "153 Episodes",
                "10 Seasons",
                "42 min",
                "https://m.media-amazon.com/images/M/MV5BMTc5ZmM0OTQtNDY4MS00ZjMyLTgwYzgtOGY0Y2VlMWFmNDU0XkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg",
                "Frank Darabont"
            )
        )

        return tvShow
    }

    fun generateRemoteDataMovie(): List<MovieResponse> {
        val movie = ArrayList<MovieResponse>()
        movie.add(
            MovieResponse(
                "M1",
                "Alita: Battle Angel",
                "14 February, 2019",
                "Rating: 72%· \u200E6,558 Ratings",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure",
                "English",
                "2 hr 2 min",
                "https://m.media-amazon.com/images/M/MV5BMTQzYWYwYjctY2JhZS00NTYzLTllM2UtZWY5ZTk0NmYwYzIyXkEyXkFqcGdeQXVyMzgxODM4NjM@._V1_.jpg",
                "Robert Rodriguez"
            )
        )
        movie.add(
            MovieResponse(
                "M2",
                "Aquaman",
                "21 December, 2018",
                "Rating: 69%· \u200E10,443 Ratings",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure, Fantasy",
                "English",
                "2h 23m",
                "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_.jpg",
                "James Wan"
            )
        )
        movie.add(
            MovieResponse(
                "M3",
                "Bohemian Rhapsody",
                "23 October, 2018",
                "Rating: 80%· \u200E13,228 Ratings",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Music, Drama, History",
                "English",
                "2h 15m",
                "https://m.media-amazon.com/images/M/MV5BMTA2NDc3Njg5NDVeQTJeQWpwZ15BbWU4MDc1NDcxNTUz._V1_.jpg",
                "Anthony McCarten"
            )
        )
        movie.add(
            MovieResponse(
                "M4",
                "Creed II",
                "21 November, 2018",
                "Rating: 69%· \u200E3,717 Ratings",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Drama",
                "English",
                "2h 10m",
                "https://m.media-amazon.com/images/M/MV5BYmEyNWZhM2YtZDAyNi00ZjYzLWI2ZWMtOWM4ZmI1MDE0OWNmXkEyXkFqcGdeQXVyMjMwNDgzNjc@._V1_.jpg",
                "Sylvester Stallone"
            )
        )
        movie.add(
            MovieResponse(
                "M5",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "16 November, 2018",
                "Rating: 69%. \u200E8,097 Ratings",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Adventure, Fantasy, Drama",
                "English",
                "2h 14m",
                "https://m.media-amazon.com/images/M/MV5BYWVlMDI5N2UtZTIyMC00NjZkLWI5Y2QtODM5NGE5MzA0NmVjXkEyXkFqcGdeQXVyNzU3NjUxMzE@._V1_FMjpg_UX1000_.jpg",
                "David Yates"
            )
        )
        movie.add(
            MovieResponse(
                "M6",
                "How to Train Your Dragon: The Hidden World",
                "09 January, 2019",
                "Rating: 78%. \u200E4,535 Ratings",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "English",
                "1h 44m",
                "poster_how_to_train",
                "Dean DeBlois"
            )
        )
        movie.add(
            MovieResponse(
                "M6",
                "How to Train Your Dragon: The Hidden World",
                "09 January, 2019",
                "Rating: 78%. \u200E4,535 Ratings",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "English",
                "1h 44m",
                "https://m.media-amazon.com/images/M/MV5BMjIwMDIwNjAyOF5BMl5BanBnXkFtZTgwNDE1MDc2NTM@._V1_.jpg",
                "Dean DeBlois"
            )
        )
        movie.add(
            MovieResponse(
                "M7",
                "Avengers: Infinity War",
                "27 April, 2018",
                "Rating: 83%. \u200E21,772 Ratings",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Adventure, Action, Science Fiction",
                "English",
                "2h 29m",
                "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_.jpg",
                "Joe Russo, Anthony Russo"
            )
        )
        movie.add(
            MovieResponse(
                "M8",
                "Mary Queen of Scots",
                "21 December, 2018",
                "Rating: 66%. \u200E1,339 Ratings",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                "Drama, History",
                "English",
                "2h 4m",
                "https://m.media-amazon.com/images/M/MV5BNDVmOGI4MTMtYmNmNC00MTliLTlkYjQtYmU2N2EyNDk2YTAwXkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_.jpg",
                "Josie Rourke"
            )
        )
        movie.add(
            MovieResponse(
                "M9",
                "Master Z: Ip Man Legacy",
                "26 December, 2018",
                "Rating: 66%. \u200E327 Ratings",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "Actions",
                "Cantonese",
                "1h 47m",
                "https://m.media-amazon.com/images/M/MV5BMTYxNzA0ODQyMF5BMl5BanBnXkFtZTgwNzUwNTg1NzM@._V1_.jpg",
                "Yuen Woo-ping"
            )
        )
        movie.add(
            MovieResponse(
                "M10",
                "Mortal Engines",
                "14 December, 2018",
                "Rating: 61%. \u200E3,371 Ratings",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "Adventure, Science Fiction",
                "English",
                "2h 9m",
                "https://m.media-amazon.com/images/M/MV5BNzY1MDA2OTQ0N15BMl5BanBnXkFtZTgwMTkzNjU2NTM@._V1_.jpg",
                "Christian Rivers"
            )
        )


        return movie
    }
    fun generateRemoteDataTvShow(): List<TvShowResponse> {

        val tvShow = ArrayList<TvShowResponse>()
        tvShow.add(
            TvShowResponse(
                "T1",
                "Game of Thrones",
                "April 17, 2011",
                "Rating: 9.3/10 · \u200E1,808,911 votes",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Serial, Epic, Tragedy, Action, Fantasy television, Action fiction, Drama, Suspense, Medieval fantasy, Thriller, Adventure, Special Interest",
                "English",
                "73 Episode",
                "8 Seasons",
                "57 min",
                "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg",
                "David Benioff, D.B. Weiss"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T2",
                "Doom Patrol",
                "15 February, 2019",
                "Rating: 76%. \u200E827 Ratings",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "English",
                "24 Episodes",
                "2 Seasons",
                "60 min",
                "https://m.media-amazon.com/images/M/MV5BM2I1OWZiNTUtZGNmOS00NGNiLTlhZmItOTU3MGRlMWFkYWU3XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg",
                "Jeremy Carver"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T3",
                "Arrow",
                "10 October, 2012",
                "Rating: 66%. \u200E4,536 Ratings",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Crime, Drama, Mystery, Action & Adventure",
                "English",
                "170 Episodes",
                "8 Seasons",
                "42 min",
                "https://m.media-amazon.com/images/M/MV5BMTI0NTMwMDgtYTMzZC00YmJhLTg4NzMtMTc1NjI4MWY4NmQ4XkEyXkFqcGdeQXVyNTY3MTYzOTA@._V1_.jpg",
                "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T4",
                "Family Guy",
                "31 January, 1999",
                "Rating: 70%. \u200E2,763 Ratings",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Animation, Comedy",
                "English",
                "369 Episodes",
                "19 Seasons",
                "22 min",
                "https://m.media-amazon.com/images/M/MV5BODEwZjEzMjAtNjQxMy00Yjc4LWFlMDAtYjhjZTAxNDU3OTg3XkEyXkFqcGdeQXVyOTM2NTM4MjA@._V1_.jpg",
                "Seth MacFarlane"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T5",
                "Flash",
                "7 October, 2014",
                "Rating: 66%. \u200E4,536 Ratings",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Drama, Sci-Fi & Fantasy",
                "English",
                "142 Episodes",
                "7 Seasons",
                "44 min",
                "https://m.media-amazon.com/images/M/MV5BMDIzNzYwNTctZWY4Mi00YjQ2LWI5YWYtMzdmNDgwMGI4Yzk1XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg",
                "Greg Berlanti, Geoff Johns, Andrew Kreisberg"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T6",
                "Gotham",
                "22 September, 2014",
                "Rating: 75%. \u200E2,296 Ratings",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Drama, Crime, Sci-Fi & Fantasy",
                "English",
                "100 Episodes",
                "5 Seasons",
                "43 min",
                "https://m.media-amazon.com/images/M/MV5BMTU5NjQ2MTU4NV5BMl5BanBnXkFtZTgwOTYyNTAwNzM@._V1_.jpg",
                "Bruno Heller"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T7",
                "Marvel's Iron Fist",
                "17 March, 2017",
                "Rating: 66%. \u200E 1,604 Ratings",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "English",
                "23 Episodes",
                "2 Seasons",
                "55 min",
                "https://m.media-amazon.com/images/M/MV5BMjI5Mjg1NDcyOV5BMl5BanBnXkFtZTgwMjAxOTQ5MTI@._V1_.jpg",
                "Scott Buck"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T8",
                "Supergirl",
                "26 October, 2015",
                "Rating: 73%. \u200E3,060 Ratings",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Drama, Sci-Fi & Fantasy, Action & Adventure",
                "English",
                "113 Episodes",
                "6 Seasons",
                "42 min",
                "https://m.media-amazon.com/images/M/MV5BODQ3MmFkZjQtZDVlYi00NTA3LWE2YTMtZmM5NzNkZGE5ZTdjXkEyXkFqcGdeQXVyMTA1OTAyOTI@._V1_.jpg",
                "Greg Berlanti, Ali Adler, Andrew Kreisberg"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T9",
                "Riverdale",
                "26 January, 2017",
                "Rating: %. \u200E Ratings",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Mystery, Drama, Crime",
                "English",
                "86 Episodes",
                "5 Seasons",
                "45 min",
                "https://m.media-amazon.com/images/M/MV5BZTg3OTczYmMtMWIxNi00NWIzLTg3ZjAtZjRkMTNkNGQ5Y2E0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg",
                "Roberto Aguirre-Sacasa"
            )
        )
        tvShow.add(
            TvShowResponse(
                "T10",
                "The Walking Dead",
                "31 October, 2010",
                "Rating: %. \u200E Ratings",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Action & Adventure, Drama, Sci-Fi & Fantasy\n",
                "English",
                "153 Episodes",
                "10 Seasons",
                "42 min",
                "https://m.media-amazon.com/images/M/MV5BMTc5ZmM0OTQtNDY4MS00ZjMyLTgwYzgtOGY0Y2VlMWFmNDU0XkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg",
                "Frank Darabont"
            )
        )

        return tvShow
    }


}