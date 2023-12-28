package com.example.appproyecto.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.*
import com.example.appproyecto.data.clienteData
import com.example.appproyecto.data.menuData
import com.example.appproyecto.databinding.ItemClienteBinding

class pUsuarioAdapter (var cliente :List<clienteData> = emptyList()): RecyclerView.Adapter<pUsuarioAdapter.petAdapterViewHolder>(){
    //Funciones para manipular la edicion y eliminacion de registros


    //crear el View Holder
    inner class  petAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var binding : ItemClienteBinding = ItemClienteBinding.bind(itemView)
        fun bind(pet: clienteData){
            binding.txtNombreClienteLis.text=pet.nombre
            binding.txtTelefonoClienteLis.text=pet.telefono
            binding.txtEmailClienteLis.text=pet.email

            if(global.rolGlobal=="0"){
                binding.btnEliminarCliente.visibility=View.INVISIBLE
                binding.btnEditarCliente.visibility=View.INVISIBLE
            }

            binding.btnEliminarCliente.setOnClickListener{
                //setOnClickListenerPetDelete(pet)
                global.idClienteSeleccionado=pet.id
                global.nombreClienteSeleccionado=pet.nombre
                global.apellidoClienteSeleccionado=pet.apellido
                global.telefonoClienteSeleccionado=pet.telefono
                global.emailClienteSeleccionado=pet.email
                global.rolClienteSeleccionado=pet.rol
                global.contrasenaClienteSeleccionado=pet.contrasena
                global.proceso=3
                val intent = Intent(itemView.context, registrarUsuarioActivity::class.java)
                itemView.context.startActivity(intent)
            }
            binding.btnEditarCliente.setOnClickListener{
                //setOnClickListenerPetEdit(pet)
                global.idClienteSeleccionado=pet.id
                global.nombreClienteSeleccionado=pet.nombre
                global.apellidoClienteSeleccionado=pet.apellido
                global.telefonoClienteSeleccionado=pet.telefono
                global.emailClienteSeleccionado=pet.email
                global.rolClienteSeleccionado=pet.rol
                global.contrasenaClienteSeleccionado=pet.contrasena
                global.proceso=2
                val intent = Intent(itemView.context, registrarUsuarioActivity::class.java)
                itemView.context.startActivity(intent)

            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): petAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente,parent,false)
        return petAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: petAdapterViewHolder, position: Int) {
        val pet = cliente[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return  cliente.size
    }
    fun updateListPets(pets: List<clienteData>){
        this.cliente = pets
        notifyDataSetChanged()
    }


}