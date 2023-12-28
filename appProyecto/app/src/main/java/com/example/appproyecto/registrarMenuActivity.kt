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

    lateinit var boton : Button;

    var URL: String="http://192.168.101.5:8082/comida/menu/registrar.php";

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

        registrar()
    }

    fun registrar(){

        this.boton.setOnClickListener {

            enviarValores()
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

                if(!response.trim().equals("No hay datos")){
                    println(response.trim())
                    Toast.makeText(this,"Correcto", Toast.LENGTH_LONG).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,loginActivity::class.java)

                    startActivity(intent)
                }else{

                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
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