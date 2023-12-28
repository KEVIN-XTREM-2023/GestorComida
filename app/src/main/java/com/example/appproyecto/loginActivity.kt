package com.example.appproyecto


import android.annotation.SuppressLint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


import org.w3c.dom.Text

class loginActivity : AppCompatActivity() {


    lateinit var ingresoUsuario : EditText;
    lateinit var ingresoContrasena : EditText;
    lateinit var textusuario : TextView;
    lateinit var textContrasena : TextView;
    lateinit var boton : Button;
    lateinit var botonRegistrar : Button;
    var URL: String="http://10.79.7.28:8082/comida/administracion/login.php";

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
            this.boton = findViewById(R.id.btnIngresar)
            this.botonRegistrar = findViewById(R.id.btnRegistrar)
            this.ingresoUsuario = findViewById(R.id.edittxtUsuario)
            this.ingresoContrasena = findViewById(R.id.edittxtcontrasenia)
            this.textusuario = findViewById(R.id.mensajeUsuario)
            this.textContrasena = findViewById(R.id.mensajeContrasenia)

            this.textusuario.visibility = View.INVISIBLE
            this.textContrasena.visibility = View.INVISIBLE


        //iniciarSesion()
        siguiente()
            registrarAbrir()

    }
    fun registrarAbrir(){

        this.botonRegistrar.setOnClickListener {
            global.proceso=1
            val intent = Intent(this,registrarUsuarioActivity::class.java)
            startActivity(intent)

        }
    }


    fun siguiente(){

        this.boton.setOnClickListener {
            iniciarSesion()
        }
    }

    private fun iniciarSesion(){
        var email = this.ingresoUsuario.text.toString().trim()
        var contrase = this.ingresoContrasena.text.toString().trim()
        if(this.controlDeErrores()){
            //siguiente()
            println("Entro1"+email)
            var requestQueue:RequestQueue=Volley.newRequestQueue(this)
            var stringRequest:StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->

                println(response.trim())
                if(response.trim().length>0) {
                    if (!response.trim().equals("[]")) {
                    if (!response.trim().equals("\"Los datos son incorrectos\"")) {

                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                        convertirJson(response.trim())
                    } else {

                        Toast.makeText(this, "Los Datos son Incorrectos", Toast.LENGTH_LONG).show()
                    }
                    } else {

                        Toast.makeText(this, "Los Datos son Incorrectos", Toast.LENGTH_LONG).show()
                    }

                } else {

                    Toast.makeText(this, "Los Datos son Incorrectos", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("email",email)
                    params.put("contrasena",contrase)
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
        var id:String="";
        var rol:String="";
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)
            println("cedula--->"+JSONObject.getString("nombre").toString())
            nombre=JSONObject.getString("nombre").toString()
            id=JSONObject.getString("id").toString()
            rol=JSONObject.getString("rol").toString()
            x++
        }

        val intent = Intent(this,opcionesActivity::class.java)

        global.id=id
        global.nombre=nombre
        global.rolGlobal=rol

        startActivity(intent)
    }

    fun  controlDeErrores():Boolean{


        if(this.ingresoUsuario.text.toString().length<=0){
            Toast.makeText(applicationContext, "Usuario es Obligatorio",Toast.LENGTH_LONG).show()
            this.textusuario.visibility = View.VISIBLE
            return false;
        }else{
            this.textusuario.visibility = View.INVISIBLE
        }
        if(this.ingresoContrasena.text.toString().length<=0){
            Toast.makeText(applicationContext, "Contraseña es Obligatoria",Toast.LENGTH_LONG).show()
            this.textContrasena.visibility = View.VISIBLE
            return false;
        }else{
            this.textContrasena.visibility = View.INVISIBLE
        }
        return true
    }

    fun consultarDatos(){





    }

}