package com.example.groovehub_app.api
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult

class ApiController {

    private fun parseTrackSearchResults(searchResults: SpotifySearchResult): List<List<String>> {
        var resultSet: MutableList<List<String>> = mutableListOf(listOf())

        for (s in searchResults.tracks!!.items) {
            var singleResultSet = mutableListOf<String>()
            singleResultSet.add(s.artists[0].name)
            singleResultSet.add(s.name)
            //singleResultSet.add(s.externalUrls.spotify.toString())
            singleResultSet.add(s.album.name)
            singleResultSet.add(s.album.releaseDate.toString())
            resultSet.add(singleResultSet)
        }
        return resultSet
    }
}






