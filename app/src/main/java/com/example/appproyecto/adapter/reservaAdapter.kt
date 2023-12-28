package com.example.appproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.appproyecto.R
import com.example.appproyecto.data.menuData
import com.example.appproyecto.data.reservaData

class reservaAdapter (context: Context, dataArrayList: ArrayList<reservaData?>?):
    ArrayAdapter<reservaData?>(context, R.layout.item_reserva, dataArrayList!!){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)

        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_reserva, parent, false)
        }

        val listClienteR = view?.findViewById<TextView>(R.id.txtClienteReservaLis)
        val listMenuR = view?.findViewById<TextView>(R.id.txtMenuReservaLis)
        val listCantidadR = view?.findViewById<TextView>(R.id.txtCantidadReservaLis)


        if (listClienteR != null) {
            if (listData != null) {
                listClienteR.text = listData.cliente
            }
        }
        if (listMenuR != null) {
            if (listData != null) {
                listMenuR.text = listData.menu
            }
        }

        if (listCantidadR != null) {
            if (listData != null) {
                listCantidadR.text = listData.cantidad
            }
        }



        return view!!
    }
}