package com.example.appproyecto

import androidx.appcompat.app.AppCompatActivity

class global: AppCompatActivity() {
    companion object {
        var id = "0";
        var nombre = "";
        var rolGlobal = "";
        var idMenuSeleccionado = "";
        var nombreMenuSeleccionado = "";
        var detalleMenuSeleccionado = "";
        var precioMenuSeleccionado = "";

        var idReservaSeleccionado = "";
        var nombreReservaSeleccionado = "";
        var cantidadReservaSeleccionado = "";

        var idClienteSeleccionado = "";
        var nombreClienteSeleccionado = "";
        var apellidoClienteSeleccionado = "";
        var telefonoClienteSeleccionado = "";
        var emailClienteSeleccionado = "";
        var rolClienteSeleccionado = "";
        var contrasenaClienteSeleccionado = "";
        var proceso = 0;
    }
}