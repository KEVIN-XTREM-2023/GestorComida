package com.example.appproyecto.adapter

import android.content.Context
import android.app.Activity
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appproyecto.R
import com.example.appproyecto.data.clienteData
import com.example.appproyecto.data.menuData

class clienteAdapter (context: Context, dataArrayList: ArrayList<clienteData?>?):
    ArrayAdapter<clienteData?>(context, R.layout.item_cliente, dataArrayList!!){

    private var list = dataArrayList
    private var context1 = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder
        val listData = getItem(position)

        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false)
            holder = ViewHolder()
            holder.name = view?.findViewById<TextView>(R.id.txtNombreClienteLis)
            view.setTag(holder)
        }else {

            //If Ouer View in not Null than Just get View using Tag and pass to holder Object.

            holder = view!!.getTag() as ViewHolder
        }

        val data = getItem(position)
        if (data != null) {
            holder.name!!.text = data.nombre
        }

        val listNombreC = view?.findViewById<TextView>(R.id.txtNombreClienteLis)
        val listTelefonoC = view?.findViewById<TextView>(R.id.txtTelefonoClienteLis)
        val listEmailC = view?.findViewById<TextView>(R.id.txtEmailClienteLis)
        val listRolC = view?.findViewById<TextView>(R.id.txtRolClienteLis)
        val btnReservar = view!!.findViewById<TextView>(R.id.btnReservarMenu)
        val btnEditar = view?.findViewById<TextView>(R.id.btnEditarMenu)
        val btnEliminar = view?.findViewById<TextView>(R.id.btnEliminarMenu)



        if (listNombreC != null) {
            if (listData != null) {
                listNombreC.text = listData.nombre+ " "+listData.apellido
            }
        }
        if (listTelefonoC != null) {
            if (listData != null) {
                listTelefonoC.text = listData.telefono
            }
        }

        if (listEmailC != null) {
            if (listData != null) {
                listEmailC.text = listData.email
            }
        }

        if (listRolC != null) {
            if (listData != null) {
                listRolC.text = listData.rol
            }
        }





        return view!!
    }

    override fun getItem(position: Int): clienteData? {
        //Return the Data at Position of ArrayList.
        return list?.get(position)
    }


    //Auto Generated Method

    override fun getItemId(position: Int): Long {
        return 0
    }

    //Auto Generated Method

    override fun getCount(): Int {

        //Return Size Of ArrayList
        return list?.size ?: 0
    }

    class ViewHolder(){
        var name: TextView? = null
        var id: TextView? = null
    }
}