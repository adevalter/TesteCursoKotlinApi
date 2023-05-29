package br.com.adeweb.testecursokotlinapiimgur.api


import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val construtorRequisicao = chain.request().newBuilder()
        val requisao = construtorRequisicao.addHeader(
            "Authorization", "${RetrofitService.CLIENT_ID}"
        ).build()
        return chain.proceed( requisao)
    }
}