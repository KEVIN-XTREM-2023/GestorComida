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

class registrarMenuActivity : AppCompatActivity() {

    lateinit var ingresoNombre : EditText;
    lateinit var ingresoDetalle : EditText;
    lateinit var ingresoPrecio : EditText;

    lateinit var textNombre : TextView;
    lateinit var textDetalle : TextView;
    lateinit var textPrecio : TextView;
    lateinit var textTitulo : TextView;

    lateinit var boton : Button;

    var URL: String="http://10.79.7.28:8082/comida/menu/registrar.php";
    var URLA: String="http://10.79.7.28:8082/comida/menu/actualizar.php";
    var URLE: String="http://10.79.7.28:8082/comida/menu/eliminar.php";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_menu)

        this.boton = findViewById(R.id.btnEnviarRegistroMenu)
        this.ingresoNombre = findViewById(R.id.editNombresMenuRegistro)
        this.ingresoDetalle = findViewById(R.id.editDetalleMenu)
        this.ingresoPrecio = findViewById(R.id.editPrecioMenu)

        this.textNombre = findViewById(R.id.mensajeNombreMenu)
        this.textDetalle = findViewById(R.id.mensajeDetalleMenu)
        this.textPrecio  = findViewById(R.id.mensajePrecioMenu)
        this.textTitulo  = findViewById(R.id.textViewTituloRegMenu)


        this.textNombre.visibility = View.INVISIBLE
        this.textDetalle.visibility = View.INVISIBLE
        this.textPrecio.visibility = View.INVISIBLE
        this.textTitulo.setText("Registro")
        if(global.proceso>=2){
            this.ingresoNombre.setText(global.nombreMenuSeleccionado)
            this.ingresoDetalle.setText(global.detalleMenuSeleccionado)
            this.ingresoPrecio.setText(global.precioMenuSeleccionado)
            this.textTitulo.setText("Actualización")
            if(global.proceso==3) {
                this.ingresoNombre.isEnabled=false
                this.ingresoDetalle.isEnabled=false
                this.ingresoPrecio.isEnabled=false
                this.textTitulo.setText("Eliminar")
            }
        }
        registrar()
    }

    fun registrar(){

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

    private fun enviarValores(){
        var nombre = this.ingresoNombre.text.toString().trim()
        var detalle = this.ingresoDetalle.text.toString().trim()
        var precio = this.ingresoPrecio.text.toString().trim()

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
                    params.put("nombre",nombre)
                    params.put("detalle",detalle)
                    params.put("precio",precio)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun enviarActualizar(){
        var nombre = this.ingresoNombre.text.toString().trim()
        var detalle = this.ingresoDetalle.text.toString().trim()
        var precio = this.ingresoPrecio.text.toString().trim()
        var id = global.idMenuSeleccionado

        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLA, Response.Listener { response ->
                println(response.trim())
                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Actualización Correcta", Toast.LENGTH_SHORT).show()
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
                    params.put("nombre",nombre)
                    params.put("detalle",detalle)
                    params.put("precio",precio)
                    params.put("id",id)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun enviarEliminar(){

        var id = global.idMenuSeleccionado

        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLE, Response.Listener { response ->
                println(response.trim())
                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Actualización Correcta", Toast.LENGTH_SHORT).show()
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

                    params.put("id",id)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    fun  controlDeErrores():Boolean{


        if(this.ingresoNombre.text.toString().length<=0){
            Toast.makeText(applicationContext, "Nombre es Obligatorio", Toast.LENGTH_LONG).show()
            this.textNombre.visibility = View.VISIBLE
            return false;
        }else{
            this.textNombre.visibility = View.INVISIBLE
        }
        if(this.ingresoDetalle.text.toString().length<=0){
            Toast.makeText(applicationContext, "Detalle es Obligatorio", Toast.LENGTH_LONG).show()
            this.textDetalle.visibility = View.VISIBLE
            return false;
        }else{
            this.textDetalle.visibility = View.INVISIBLE
        }
        if(this.ingresoPrecio.text.toString().length<=0){
            Toast.makeText(applicationContext, "Precio es Obligatorio", Toast.LENGTH_LONG).show()
            this.textPrecio.visibility = View.VISIBLE
            return false;
        }else{
            this.textPrecio.visibility = View.INVISIBLE
        }

        return true
    }
}