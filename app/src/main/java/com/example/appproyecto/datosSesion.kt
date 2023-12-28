package com.example.appproyecto

import android.app.Application

class datosSesion {




    var nombre: String = ""
        private set

    var apellido: String = ""
        private set

    var email: String = ""
        private set

    var id: String = ""
    private set

    var telefono: String = ""
        private set

    public fun setNombre(valor: String) {
        nombre = valor
    }

    @JvmName("getNombre1")
    public fun getNombre():String{
        var nom:String=nombre
        return nom
    }

    fun setApellido(valor: String) {
        apellido = valor
    }

    fun setEmail(valor: String) {
        email = valor
    }

    fun setTelefono(valor: String) {
        telefono = valor
    }

    fun setId(valor: String) {
        id = valor
    }



}