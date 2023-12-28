package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.adapter.pClienteAdapter
import com.example.appproyecto.adapter.pReservaAdapter
import com.example.appproyecto.data.menuData
import com.example.appproyecto.data.reservaData
import com.example.appproyecto.databinding.ActivityMenuBinding
import com.example.appproyecto.databinding.ActivityReservasBinding
import org.json.JSONArray

class ReservasActivity : AppCompatActivity() {

    var URL: String="http://10.79.7.28:8082/comida/reservas/lista.php";
    var URLC: String="http://10.79.7.28:8082/comida/reservas/listaCliente.php";

    private lateinit var binding: ActivityReservasBinding
    private lateinit var listData: reservaData
    var dataArrayList = ArrayList<reservaData?>()

    private val adapter : pReservaAdapter by lazy {
        pReservaAdapter()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(global.rolGlobal=="0")
        {
            traerDatosCliente();
        }else {
            traerDatos();
        }
        //inicializar()

        cargarAdaptador()
    }



    fun cargarAdaptador(){
        binding.listReserva.adapter = adapter
    }


    private fun traerDatos(){
        var id = global.id

        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->
            println("id--> "+id)
            println("Entro1--> "+response.trim())

            if(response.trim().length>0) {
                if (!response.trim().equals("[]")) {

                    //Toast.makeText(this,"Correcto", Toast.LENGTH_LONG).show()
                    convertirJson(response.trim())
                }else{

                    Toast.makeText(this,"No hay datos registrados", Toast.LENGTH_LONG).show()
                }
            }else{

                Toast.makeText(this,"No hay datos registrados", Toast.LENGTH_LONG).show()
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String, String>()
                //params.put("id_tax_per",id.toString())
                return params
            }
        }
        requestQueue.add(stringRequest)

        println("Entro3")
    }

    private fun traerDatosCliente(){
        var id = global.id

        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLC, Response.Listener { response ->
            println("id--> "+id)
            println("Entro1--> "+response.trim())

            if(response.trim().length>0) {
                if (!response.trim().equals("[]")) {

                    //Toast.makeText(this,"Correcto", Toast.LENGTH_LONG).show()
                    convertirJson(response.trim())
                }else{

                    Toast.makeText(this,"No hay datos registrados", Toast.LENGTH_LONG).show()
                }
            }else{

                Toast.makeText(this,"No hay datos registrados", Toast.LENGTH_LONG).show()
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

        println("Entro3")
    }

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray(jsonString)


        var x=0;
        var listMenuData: List<menuData>
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            var me = JSONObject.getString("menu").toString()
            var id = JSONObject.getString("id").toString()
            var cl = "Cliente: "+JSONObject.getString("cliente").toString()
            var ca = JSONObject.getString("cantidad").toString()


            this.listData = reservaData(id,cl,me,ca)
            this.dataArrayList.add(this.listData)

            x++
        }
        println(this.dataArrayList)
        var listMenuData1: List<reservaData> = dataArrayList.toList() as List<reservaData>
        adapter.updateListPets(listMenuData1)
        //this.listAdapter = menuAdapter(this@menuActivity,this.dataArrayList)
        //this.lista.adapter = this.listAdapter
        //this.lista.isClickable = true




    }


}