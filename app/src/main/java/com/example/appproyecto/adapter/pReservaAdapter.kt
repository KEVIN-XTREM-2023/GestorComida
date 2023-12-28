package com.example.appproyecto.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.R
import com.example.appproyecto.ReservarActivity
import com.example.appproyecto.data.reservaData
import com.example.appproyecto.databinding.ItemReservaBinding
import com.example.appproyecto.global
import com.example.appproyecto.registrarMenuActivity

class pReservaAdapter (var reservas :List<reservaData> = emptyList()): RecyclerView.Adapter<pReservaAdapter.petAdapterViewHolder>(){
    //Funciones para manipular la edicion y eliminacion de registros


    //crear el View Holder
    inner class  petAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var binding : ItemReservaBinding = ItemReservaBinding.bind(itemView)
        fun bind(pet: reservaData){
            binding.txtMenuReservaLis.text=pet.cliente
            binding.txtClienteReservaLis.text=pet.menu
            binding.txtCantidadReservaLis.text=pet.cantidad


            binding.btnEliminarReserva.setOnClickListener{
                global.idReservaSeleccionado=pet.id
                global.nombreReservaSeleccionado=pet.menu
                global.cantidadReservaSeleccionado=pet.cantidad
                global.proceso=3
                val intent = Intent(itemView.context, ReservarActivity::class.java)
                itemView.context.startActivity(intent)
            }
            binding.btnEditarReserva.setOnClickListener{
                global.idReservaSeleccionado=pet.id
                global.nombreReservaSeleccionado=pet.menu
                global.cantidadReservaSeleccionado=pet.cantidad
                global.proceso=2
                val intent = Intent(itemView.context, ReservarActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): petAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva,parent,false)
        return petAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: petAdapterViewHolder, position: Int) {
        val pet = reservas[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return  reservas.size
    }
    fun updateListPets(pets: List<reservaData>){
        this.reservas = pets
        notifyDataSetChanged()
    }


}