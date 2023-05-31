package br.com.adeweb.testecursokotlinapiimgur.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.adeweb.testecursokotlinapiimgur.databinding.ItemImgurBinding
import br.com.adeweb.testecursokotlinapiimgur.model.Cats
import br.com.adeweb.testecursokotlinapiimgur.model.Image
import br.com.adeweb.testecursokotlinapiimgur.model.ListaImg
import com.squareup.picasso.Picasso

class ImgurAdapeter() : RecyclerView.Adapter<ImgurAdapeter.ImgurViewHolder>() {

    private var listaImgur = mutableListOf<ListaImg>()

    fun adicionarLista(lista: MutableList<ListaImg>){
        this.listaImgur.addAll(lista)
        notifyDataSetChanged()
    }

    inner class ImgurViewHolder(val binding : ItemImgurBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listaImg: ListaImg){
            val linkFilme = listaImg.link
            Picasso.get()
                .load(linkFilme)
                .resize(110, 150)
                .centerCrop()
                .into(binding.imgItemImgur)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgurViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImgurBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ImgurViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImgurViewHolder, position: Int) {
        val image = listaImgur[position]
        holder.bind(image)

    }

    override fun getItemCount(): Int {
        return listaImgur.size
    }

}