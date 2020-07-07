package com.gritinnovation.tmdb.network

import com.gritinnovation.tmdb.repository.DataRepository

object RepositoryFactory {

    fun createDataRepository(): DataRepository {
        val webApi = RestUtil.instance.retrofit.create(WebApi::class.java)
        return DataRepository(webApi)
    }
}