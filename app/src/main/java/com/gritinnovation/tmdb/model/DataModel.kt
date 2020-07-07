package com.gritinnovation.tmdb.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.gritinnovation.tmdb.R
import com.gritinnovation.tmdb.util.Constant

class DataModel {

    @SerializedName("page")
    @Expose
    var page: Long = 0

    @SerializedName("total_results")
    @Expose
    var totalResults: Long = 0

    @SerializedName("total_pages")
    @Expose
    var totalPages: Long = 0

    @SerializedName("results")
    @Expose
    var results: MutableList<Result>? = arrayListOf()

    class Result {

        @SerializedName("popularity")
        @Expose
        var popularity: Double = 0.0

        @SerializedName("vote_count")
        @Expose
        var voteCount: Long = 0

        @SerializedName("video")
        @Expose
        var video = false

        @SerializedName("poster_path")
        @Expose
        var posterPath = ""

        @SerializedName("id")
        @Expose
        var id: Long = 0

        @SerializedName("adult")
        @Expose
        var adult = false

        @SerializedName("backdrop_path")
        @Expose
        var backDropPath = ""

        @SerializedName("original_language")
        @Expose
        var originalLanguage = ""

        @SerializedName("original_title")
        @Expose
        var originalTitle = ""

        @SerializedName("genre_ids")
        @Expose
        var genreIds: Array<String> = arrayOf()

        @SerializedName("title")
        @Expose
        var title = ""

        @SerializedName("vote_average")
        @Expose
        var voteAverage: Double = 0.0

        @SerializedName("overview")
        @Expose
        var overview = ""

        @SerializedName("release_date")
        @Expose
        var releaseDate = ""
    }

    companion object {

        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun ImageView.loadImage(url: String?) {
            Glide.with(context)
                .load("${Constant.IMAGE_URL}$url")
                .error(R.mipmap.ic_launcher_round)
                .fallback(R.mipmap.ic_launcher_round)
                /*.apply(RequestOptions().centerCrop())*/
                .into(this)
        }
    }
}