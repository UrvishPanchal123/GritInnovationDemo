package com.gritinnovation.tmdb.repository

import com.gritinnovation.tmdb.model.DataModel
import com.gritinnovation.tmdb.network.WebApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataRepository(private val webApi: WebApi) {

    fun getDataFromServer(sortBy: String, apiKey: String): Observable<DataModel> {

        return Observable.create { emitter ->
            webApi.getMovieList(sortBy, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        emitter.onNext(it)
                    }
                }, {
                    it.printStackTrace()
                })
        }
    }
}