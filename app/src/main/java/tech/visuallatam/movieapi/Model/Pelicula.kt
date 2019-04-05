package tech.visuallatam.movieapi.Model

import android.os.Parcel
import android.os.Parcelable

data class Pelicula ( var Title : String= "N/A",
                      var Year: String= "N/A",
                      var Rated : String= "N/A",
                      var Released: String= "N/A",
                      var Runtime: String= "N/A",
                      var Genre: String= "N/A",
                      var Director: String= "N/A",
                      var Actors:String= "N/A",
                      var Plot: String= "N/A",
                      val Language : String= "N/A",
                      val imdbRating: String= "N/A",
                      val Poster: String = "N/A"): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(Rated)
        parcel.writeString(Released)
        parcel.writeString(Runtime)
        parcel.writeString(Genre)
        parcel.writeString(Director)
        parcel.writeString(Actors)
        parcel.writeString(Plot)
        parcel.writeString(Language)
        parcel.writeString(imdbRating)
        parcel.writeString(Poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }
}