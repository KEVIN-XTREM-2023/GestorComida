package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class ReservarActivity : AppCompatActivity() {

    lateinit var ingresoNombre : EditText;
    lateinit var ingresoCantidad : EditText;


    lateinit var textNombre : TextView;
    lateinit var textCantidad : TextView;
    lateinit var textTitulo : TextView;

    lateinit var boton : Button;

    var URL: String="http://10.79.7.28:8082/comida/reservas/registrar.php";
    var URLA: String="http://10.79.7.28:8082/comida/reservas/actualizar.php";
    var URLE: String="http://10.79.7.28:8082/comida/reservas/eliminar.php";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservar)

        this.boton = findViewById(R.id.btnEnviarRegistroMenu)
        this.ingresoNombre = findViewById(R.id.editNombresRecervaRegistro)
        this.ingresoCantidad = findViewById(R.id.editCantidadReservaRegistro)

        this.textNombre = findViewById(R.id.mensajeNombreReservasRegistro)
        this.textCantidad = findViewById(R.id.mensajeCantidadReservaRegistro)

        this.textTitulo = findViewById(R.id.textViewTituloRegMenu)

        this.textNombre.visibility = View.INVISIBLE
        this.textCantidad.visibility = View.INVISIBLE

        this.ingresoNombre.setText(global.nombreMenuSeleccionado)

        if(global.proceso>=2){

            this.ingresoNombre.setText(global.nombreReservaSeleccionado)
            this.ingresoCantidad.setText(global.cantidadReservaSeleccionado)
            this.textTitulo.setText("Actualización")
            this.ingresoNombre.isEnabled=false
            if(global.proceso==3) {

                this.ingresoCantidad.isEnabled=false

                this.textTitulo.setText("Eliminar")
            }
        }

        registrar()
    }

    fun registrar(){

        this.boton.setOnClickListener {

            this.boton.setOnClickListener {
                if(global.proceso==1) {
                    enviarValores()
                }else{
                    if(global.proceso==2) {
                        enviarActualizar()
                    }else{
                        enviarEliminar()
                    }
                }
            }
        }
    }

    private fun enviarValores(){
        var nombre = this.ingresoNombre.text.toString().trim()
        var cantidad = this.ingresoCantidad.text.toString().trim()
        var menu = global.idMenuSeleccionado
        var cliente = global.id


        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->

                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Registro Correcto", Toast.LENGTH_SHORT).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,menuActivity::class.java)

                    startActivity(intent)
                }else{

                    Toast.makeText(this,"Error al ingresar Información", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("cliente",cliente)
                    params.put("menu",menu)
                    params.put("cantidad",cantidad)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun enviarActualizar(){
        var cantidad = this.ingresoCantidad.text.toString().trim()
        var id = global.idReservaSeleccionado

        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLA, Response.Listener { response ->
                println("-------------------------------------------------")
                println(response.trim())
                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Actualización Correcta", Toast.LENGTH_SHORT).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,ReservasActivity::class.java)

                    startActivity(intent)
                }else{

                    Toast.makeText(this,"Error al ingresar Información", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("cantidad",cantidad)
                    params.put("id",id)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun enviarEliminar(){

        var id = global.idReservaSeleccionado

        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLE, Response.Listener { response ->
                println("-------------------------------------------------")
                println(response.trim())
                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Eliminacion Correcta", Toast.LENGTH_SHORT).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,ReservasActivity::class.java)

                    startActivity(intent)
                }else{

                    Toast.makeText(this,"Error al ingresar Información", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()

                    params.put("id",id)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    fun  controlDeErrores():Boolean{



        if(this.ingresoCantidad.text.toString().length<=0){
            Toast.makeText(applicationContext, "Cantidad es Obligatoria", Toast.LENGTH_LONG).show()
            this.textCantidad.visibility = View.VISIBLE
            return false;
        }else{
            this.textCantidad.visibility = View.INVISIBLE
        }


        return true
    }
}