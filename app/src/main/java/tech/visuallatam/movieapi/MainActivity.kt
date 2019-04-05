package tech.visuallatam.movieapi

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import tech.visuallatam.movieapi.Model.Pelicula
import tech.visuallatam.movieapi.Utilities.NetworkUtils
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var adaptador : MovieAdapter
    private lateinit var  viewManager : RecyclerView.LayoutManager
    private var movieList: ArrayList<Pelicula> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    fun initRecyclerView(){
        viewManager = LinearLayoutManager(this)
        adaptador = MovieAdapter(movieList)

        movie_list_rv.apply{
            setHasFixedSize(true)
            layoutManager  = viewManager
            adapter = adaptador

        }

        add_movie_btn.setOnClickListener { FetchMovie().execute("${movie_name_et.text}") }
    }

    fun addMovieToList(movie: Pelicula){
        movieList.add(movie)
        adaptador.changeList(movieList)
        Log.d("number", movieList.size.toString())
    }


    private inner class FetchMovie : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String): String {
            if ( params.isNullOrEmpty()) return ""

            val moviename = params[0]
            val movieurl = NetworkUtils().buildtSearchURL(moviename)

            return try{
                NetworkUtils().getResponseFromHttpUrl(movieurl)
            } catch (e : IOException){
                ""
            }
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            if(!result.isEmpty()){
                val movieJson = JSONObject(result)
                if(movieJson.getString("Response")=="True"){
                    val movie = Gson().fromJson<Pelicula>(result,Pelicula::class.java)
                    addMovieToList(movie)
                }else{
                    Snackbar.make(main_ll,"No Existe la pelicula en la base",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
