package com.example.appproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appproyecto.R
import com.example.appproyecto.data.menuData

class menuAdapter(context: Context, dataArrayList: ArrayList<menuData?>?):
    ArrayAdapter<menuData?>(context, R.layout.item_menu, dataArrayList!!){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)

        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        }

        val listNombreS = view?.findViewById<TextView>(R.id.txtNombreMenuLis)
        val listDetalleS = view?.findViewById<TextView>(R.id.txtDetalleMenuLis)
        val listPrecioS = view?.findViewById<TextView>(R.id.txtPrecioMenuLis)


        if (listNombreS != null) {
            if (listData != null) {
                listNombreS.text = listData.nombre
            }
        }
        if (listDetalleS != null) {
            if (listData != null) {
                listDetalleS.text = listData.detalle
            }
        }

        if (listPrecioS != null) {
            if (listData != null) {
                listPrecioS.text = listData.precio
            }
        }



        return view!!
    }
}