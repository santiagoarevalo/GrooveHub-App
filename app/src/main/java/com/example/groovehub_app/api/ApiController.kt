package com.example.groovehub_app.api
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult
import com.example.groovehub_app.model.Song

class ApiController {

    suspend fun searchTrack(songTitle:String): List<List<Song>> {
        val api = SpotifyApiRequest()
        api.buildSearchApi()
        val searchResults = api.trackSearch(songTitle)
        return parseTrackSearchResults(searchResults)
    }

    private fun parseTrackSearchResults(searchResults: SpotifySearchResult): List<List<Song>> {
        var resultSet: MutableList<List<Song>> = mutableListOf(listOf())

        for (s in searchResults.tracks!!.items) {
            var singleResultSet = mutableListOf<Song>()
            singleResultSet.add(Song(s.name,s.artists[0].name,s.album.name,s.album.images[0].url,s.album.releaseDate?.year.toString()))
            resultSet.add(singleResultSet)
        }
        return resultSet
    }
}






