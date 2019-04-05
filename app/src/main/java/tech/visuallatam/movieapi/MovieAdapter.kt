package tech.visuallatam.movieapi

import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_element.view.*
import tech.visuallatam.movieapi.Model.Pelicula

class MovieAdapter(var movies: List<Pelicula>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_element,p0,false  )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder (holder: MovieAdapter.ViewHolder, pos: Int) {
        holder.bind(movies[pos])
    }


    fun changeList(movies: List<Pelicula>){
        this.movies = movies
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun bind(item : Pelicula) = with(itemView){
                movie_title_cv.text = item.Title
                movie_plot_cv.text = item.Plot
                movie_rate_cv.text = item.imdbRating
                movie_runtime_cv.text  = item.Runtime
                Glide.with(itemView.context).load(item.Poster).placeholder(R.drawable.ic_launcher_background).into(movie_image_cv)
            }
    }
}