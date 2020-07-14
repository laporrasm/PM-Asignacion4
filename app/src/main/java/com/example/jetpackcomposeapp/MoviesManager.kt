package com.example.jetpackcomposeapp

import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

data class Movie(val id : String, val name : String, val time : String, val review : String) : Serializable

class MoviesManager {
    /*fun getMovies(): List<Movie> {
        val movies  = ArrayList<Movie>()
        movies.add(Movie("Avengers Endgame", "15:00", "Avengers: Endgame \u200B es una película de superhéroes estadounidense de 2019 basada en el grupo los Vengadores de Marvel Comics, producida por Marvel Studios y distribuida por Walt Disney Studios Motion Pictures."))
        movies.add(Movie("La Llorona", "17:00", "Los Ángeles, década de 1970. Un legendario fantasma que se oculta en la oscuridad de la noche aterra a los niños. Tras ignorar espeluzantes advertencias, una trabajadora social y sus hijos se adentran en un mundo sobrenatural repleto de misterio. Su única esperanza para sobrevivir a la Llorona radica en un sacerdote que practica el misticismo."))
        movies.add(Movie("Detective Pikachu", "11:00","Pokémon: Detective Pikachu es una película de fantasía\u200B dirigida por Rob Letterman y escrita por Nicole Perlman y Letterman, basada en el videojuego del mismo nombre. La película es una empresa conjunta estadounidense y japonesa producida por Legendary Pictures, The Pokémon Company, Warner Bros. Pictures y Toho."))
        return movies
    }*/
    fun getMovies(block : (ArrayList<Movie>) -> Unit) {
        val dbReference = FirebaseFirestore.getInstance()
        val collection = dbReference.collection("movies")
        val moviesList  = ArrayList<Movie>()

        collection.get().addOnSuccessListener {
            for (doc in it) {
                moviesList.add(
                    Movie(
                        doc.id,
                        doc["name"] as String,
                        doc["time"] as String,
                        doc["review"] as String
                    )
                )
            }
            block(moviesList)
        }
    }
}