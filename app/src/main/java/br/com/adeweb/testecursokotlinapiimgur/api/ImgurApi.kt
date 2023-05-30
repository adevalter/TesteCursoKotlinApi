package br.com.adeweb.testecursokotlinapiimgur.api

import br.com.adeweb.testecursokotlinapiimgur.model.Cats
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search/")
    suspend fun consultarCats(
        @Query("q") search: String
    ): Response<Cats>
}