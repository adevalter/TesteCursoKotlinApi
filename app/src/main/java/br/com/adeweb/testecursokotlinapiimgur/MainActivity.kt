package br.com.adeweb.testecursokotlinapiimgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.adeweb.testecursokotlinapiimgur.api.ImgurApi
import br.com.adeweb.testecursokotlinapiimgur.api.RetrofitService
import br.com.adeweb.testecursokotlinapiimgur.databinding.ActivityMainBinding
import br.com.adeweb.testecursokotlinapiimgur.model.Cats
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
    }

    override fun onStop() {
        super.onStop()
    }



    private fun consultarCats(){
        CoroutineScope(Dispatchers.IO).launch{
            var resposta:  Response<Cats>? = null

            try {
                resposta = ImgurApi.consultarCats("cats")
            }catch (e: java.lang.Exception){
                withContext(Dispatchers.Main){
                    exibirMensagem("Não foi possível efetuar a consulta ${resposta}")
                }
            }

            if (resposta!=null) {
                if(resposta.isSuccessful){
                    Log.i("info_imgur", "consultarCats: $resposta")
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