package tech.visuallatam.movieapi.Utilities

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val MOVIES_API_BASEURL = "http://www.omdbapi.com/"
    val TOKEN_API = "6fd8f447"

    fun buildtSearchURL(moviename  :String) : URL{
        val builtUri = Uri.parse(MOVIES_API_BASEURL)
                .buildUpon()
                .appendQueryParameter("apikey",TOKEN_API)
                .appendQueryParameter("t",moviename)
                .build()

        return try{
            URL(builtUri.toString())
        }catch ( e : MalformedURLException){
            URL("")
        }
    }




@Throws(IOException::class)
fun getResponseFromHttpUrl(url: URL):String{
    val urlConnection = url.openConnection() as HttpURLConnection
    try {
        val lel = urlConnection.inputStream
        val scanner = Scanner(lel)
        scanner.useDelimiter("\\A")

        val hasInput = scanner.hasNext()
        return if(hasInput){
            scanner.next()
        }else{
            ""
        }
    }finally {
        urlConnection.disconnect()
    }
}




}