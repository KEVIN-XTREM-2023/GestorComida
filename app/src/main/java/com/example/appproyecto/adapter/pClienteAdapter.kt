package com.example.appproyecto.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.*
import com.example.appproyecto.data.menuData
import com.example.appproyecto.databinding.ItemMenuBinding

class pClienteAdapter
    (var menus :List<menuData> = emptyList()): RecyclerView.Adapter<pClienteAdapter.petAdapterViewHolder>(){
    //Funciones para manipular la edicion y eliminacion de registros

    lateinit var  setOnClickListenerPetEdit: (menuData)->Unit
    lateinit var  setOnClickListenerPetReserv:(menuData)->Unit
    lateinit var  setOnClickListenerPetDelete:(menuData)->Unit

    //crear el View Holder
    inner class  petAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var binding : ItemMenuBinding = ItemMenuBinding.bind(itemView)
        fun bind(pet: menuData){
            binding.txtNombreMenuLis.text=pet.nombre
            binding.txtDetalleMenuLis.text=pet.detalle
            binding.txtPrecioMenuLis.text=pet.precio
            if(global.rolGlobal=="0"){

                binding.btnEliminarMenu.visibility=View.INVISIBLE
                binding.btnEditarMenu.visibility=View.INVISIBLE
            }

            binding.btnReservarMenu.setOnClickListener{
               //setOnClickListenerPetReserv(pet)
                global.proceso=1
                global.idMenuSeleccionado=pet.id
                global.nombreMenuSeleccionado=pet.nombre
                val intent = Intent(itemView.context, ReservarActivity::class.java)
                itemView.context.startActivity(intent)



                println("----------------------------------------->")

            }
            binding.btnEliminarMenu.setOnClickListener{
                //setOnClickListenerPetDelete(pet)
                global.idMenuSeleccionado=pet.id
                global.nombreMenuSeleccionado=pet.nombre
                global.detalleMenuSeleccionado=pet.detalle
                global.precioMenuSeleccionado=pet.precio
                global.proceso=3
                val intent = Intent(itemView.context, registrarMenuActivity::class.java)
                itemView.context.startActivity(intent)
            }
            binding.btnEditarMenu.setOnClickListener{
                //setOnClickListenerPetEdit(pet)
                global.idMenuSeleccionado=pet.id
                global.nombreMenuSeleccionado=pet.nombre
                global.detalleMenuSeleccionado=pet.detalle
                global.precioMenuSeleccionado=pet.precio
                global.proceso=2
                val intent = Intent(itemView.context, registrarMenuActivity::class.java)
                itemView.context.startActivity(intent)

            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): petAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return petAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: petAdapterViewHolder, position: Int) {
        val pet = menus[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return  menus.size
    }
    fun updateListPets(pets: List<menuData>){
        this.menus = pets
        notifyDataSetChanged()
    }


}