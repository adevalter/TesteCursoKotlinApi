package br.com.adeweb.testecursokotlinapiimgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.adeweb.testecursokotlinapiimgur.api.ImgurApi
import br.com.adeweb.testecursokotlinapiimgur.api.RetrofitService
import br.com.adeweb.testecursokotlinapiimgur.databinding.ActivityMainBinding
import br.com.adeweb.testecursokotlinapiimgur.model.Cats
import br.com.adeweb.testecursokotlinapiimgur.model.Image
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate( layoutInflater)
    }

    private val ImgurApi by lazy {
        RetrofitService.recuperarApi(ImgurApi::class.java)
    }

    var jobImgurCats: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
    }

    override fun onStart() {
        super.onStart()
        consultarCats()
    }

    override fun onStop() {
        super.onStop()
        jobImgurCats?.cancel()
    }



    private fun consultarCats(){
        jobImgurCats =  CoroutineScope(Dispatchers.IO).launch{
            var resposta:  Response<Cats>? = null

            try {
                resposta = ImgurApi.consultarCats("cats")
            }catch (e: java.lang.Exception){
                withContext(Dispatchers.Main){
                    exibirMensagem("Não foi possível efetuar a consulta $resposta")
                }
            }

            if (resposta!=null) {
                if(resposta.isSuccessful){
                    val imgurResposta = resposta.body()
                    val listaImgur = imgurResposta?.data
                    if(listaImgur != null && listaImgur.isNotEmpty()){
                     //   Log.i("info_imgur_size", "consultarCats:   - ${listaImgur[0].images}. ")
                        listaImgur.forEach{ data ->

                           val qtde = data.images_count
                            if(qtde > 1){
                                val linkSite = data.images.forEach{ img ->
                                   val tipo = img.type
                                   val linksie = img.link
                                    Log.i("info_imgur_img", "consultarCats: $tipo $linksie")
                                }
                            }

                                Log.i("info_imgur_img", "consultarCats: $qtde ")

                        }
                    }

                 //   Log.i("info_imgur", "consultarCats: $listaImgur. ")
                }else{
                    Log.i("info_imgur", "consultarCats: $resposta")
                }

            }else{
                Log.i("info_imgur", "consultarCats: $resposta")
            }
        }
    }

    private fun exibirMensagem(mensagem: String){
        Toast.makeText(
            applicationContext,
            mensagem,
            Toast.LENGTH_LONG
        ).show()
    }

}