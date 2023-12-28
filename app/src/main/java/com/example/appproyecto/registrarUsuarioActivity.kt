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

class registrarUsuarioActivity : AppCompatActivity() {

    lateinit var ingresoNombre : EditText;
    lateinit var ingresoApellido : EditText;
    lateinit var ingresoEmail : EditText;
    lateinit var ingresoContrasena : EditText;
    lateinit var ingresotelefono : EditText;
    lateinit var textNombre : TextView;
    lateinit var textApellido : TextView;
    lateinit var textEmail : TextView;
    lateinit var textTelefono : TextView;
    lateinit var textContrasena : TextView;
    lateinit var textTitulo : TextView;
    lateinit var boton : Button;

    var URL: String="http://10.79.7.28:8082/comida/usuario/registrar.php";
    var URLA: String="http://10.79.7.28:8082/comida/usuario/actualizar.php";
    var URLE: String="http://10.79.7.28:8082/comida/usuario/eliminar.php";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        this.boton = findViewById(R.id.btnEnviarRegistroUsuario)
        this.ingresoNombre = findViewById(R.id.editNombresRegistro)
        this.ingresoApellido = findViewById(R.id.editApellidoRegistro)
        this.ingresoContrasena = findViewById(R.id.editContrasenaRegistro)
        this.ingresotelefono = findViewById(R.id.editTelefonoRegistro)
        this.ingresoEmail = findViewById(R.id.editEmailRegistro)
        this.textNombre = findViewById(R.id.mensajeUsuarioRe)
        this.textApellido = findViewById(R.id.mensajeApellidoRe)
        this.textTelefono = findViewById(R.id.mensajeTelefonoRe)
        this.textEmail   = findViewById(R.id.mensajeEmailRe)
        this.textContrasena = findViewById(R.id.mensajeContraseniaRe)
        this.textTitulo = findViewById(R.id.textView3)


        this.textNombre.visibility = View.INVISIBLE
        this.textApellido.visibility = View.INVISIBLE
        this.textTelefono.visibility = View.INVISIBLE
        this.textEmail.visibility = View.INVISIBLE
        this.textContrasena.visibility = View.INVISIBLE

        if(global.proceso>=2){
            this.ingresoNombre.setText(global.nombreClienteSeleccionado)
            this.ingresoApellido.setText(global.apellidoClienteSeleccionado)
            this.ingresoContrasena.setText(global.contrasenaClienteSeleccionado)
            this.ingresotelefono.setText(global.telefonoClienteSeleccionado)
            this.ingresoEmail.setText(global.emailClienteSeleccionado)
            this.ingresoContrasena.setText(global.contrasenaClienteSeleccionado)

            this.textTitulo.setText("Actualización")
            if(global.proceso==3) {
                this.ingresoNombre.isEnabled=false
                this.ingresoApellido.isEnabled=false
                this.ingresoContrasena.isEnabled=false
                this.ingresotelefono.isEnabled=false
                this.ingresoEmail.isEnabled=false
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
        var apellido = this.ingresoApellido.text.toString().trim()
        var email = this.ingresoEmail.text.toString().trim()
        var telefono = this.ingresotelefono.text.toString().trim()
        var contrase = this.ingresoContrasena.text.toString().trim()
        if(this.controlDeErrores()){
            //siguiente()
            println("Entro1"+email)
            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->

                println(response.trim())
                if(!response.trim().equals("\"Los datos son incorrectos\"")){

                    Toast.makeText(this,"Registro Correcto", Toast.LENGTH_LONG).show()
                    convertirJson(response.trim())
                    val intent = Intent(this,loginActivity::class.java)

                    startActivity(intent)
                }else{

                    Toast.makeText(this,"No se pudo crear el usuario", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("nombre",nombre)
                    params.put("apellido",apellido)
                    params.put("telefono",telefono)
                    params.put("email",email)
                    params.put("contrasena",contrase)
                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun enviarActualizar(){
        var nombre = this.ingresoNombre.text.toString().trim()
        var apellido = this.ingresoApellido.text.toString().trim()
        var telefono = this.ingresotelefono.text.toString().trim()
        var email = this.ingresoEmail.text.toString().trim()
        var contrasena = this.ingresoContrasena.text.toString().trim()
        var id = global.idClienteSeleccionado

        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLA, Response.Listener { response ->
                println("-------------------------------------------------------")
                println(response.trim())
                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Actualización Correcta", Toast.LENGTH_SHORT).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,ClientesActivity::class.java)

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
                    params.put("apellido",apellido)
                    params.put("telefono",telefono)
                    params.put("email",email)
                    params.put("contrasena",contrasena)
                    params.put("id",id)

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun enviarEliminar(){

        var id = global.idClienteSeleccionado

        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLE, Response.Listener { response ->
                println(response.trim())
                if (!response.trim().equals("\"Los datos son incorrectos\"")) {
                    println(response.trim())
                    Toast.makeText(this,"Actualización Correcta", Toast.LENGTH_SHORT).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,ClientesActivity::class.java)

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

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray(jsonString)

        //val list = ArrayList<>

        var x=0;
        var globalDatos=datosSesion()
        var nombre:String="";
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            //nombre=JSONObject.getString("nombre").toString()
            x++
        }

        val intent = Intent(this,loginActivity::class.java)

        startActivity(intent)
    }

    fun  controlDeErrores():Boolean{


        if(this.ingresoNombre.text.toString().length<=0){
            Toast.makeText(applicationContext, "Nombre es Obligatorio", Toast.LENGTH_LONG).show()
            this.textNombre.visibility = View.VISIBLE
            return false;
        }else{
            this.textNombre.visibility = View.INVISIBLE
        }
        if(this.ingresoApellido.text.toString().length<=0){
            Toast.makeText(applicationContext, "Apellido es Obligatorio", Toast.LENGTH_LONG).show()
            this.textApellido.visibility = View.VISIBLE
            return false;
        }else{
            this.textApellido.visibility = View.INVISIBLE
        }
        if(this.ingresoEmail.text.toString().length<=0){
            Toast.makeText(applicationContext, "Email es Obligatorio", Toast.LENGTH_LONG).show()
            this.textEmail.visibility = View.VISIBLE
            return false;
        }else{
            this.textEmail.visibility = View.INVISIBLE
        }
        if(this.ingresotelefono.text.toString().length<=0){
            Toast.makeText(applicationContext, "Telefono es Obligatorio", Toast.LENGTH_LONG).show()
            this.textTelefono.visibility = View.VISIBLE
            return false;
        }else{
            this.textTelefono.visibility = View.INVISIBLE
        }

        if(this.ingresoContrasena.text.toString().length<=0){
            Toast.makeText(applicationContext, "Contraseña es Obligatorio", Toast.LENGTH_LONG).show()
            this.textContrasena.visibility = View.VISIBLE
            return false;
        }else{
            this.textContrasena.visibility = View.INVISIBLE
        }
        return true
    }
}