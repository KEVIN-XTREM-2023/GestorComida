package com.example.appproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.adapter.clienteAdapter
import com.example.appproyecto.adapter.menuAdapter
import com.example.appproyecto.adapter.pReservaAdapter
import com.example.appproyecto.adapter.pUsuarioAdapter
import com.example.appproyecto.data.clienteData
import com.example.appproyecto.data.menuData
import com.example.appproyecto.data.reservaData
import com.example.appproyecto.databinding.ActivityClientesBinding
import com.example.appproyecto.databinding.ActivityMenuBinding
import com.example.appproyecto.databinding.ActivityReservasBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class ClientesActivity : AppCompatActivity() {

    lateinit var lista : ListView;
    lateinit var arayLista : ArrayList<HashMap<String,String>>;

    var URL: String="http://10.79.7.28:8@082/comida/usuario/lista.php";
    var URLC: String="http://10.79.7.28:8082/comida/usuario/listaCliente.php";

    private lateinit var binding: ActivityClientesBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: clienteData
    var dataArrayList = ArrayList<clienteData?>()


    private val adapter : pUsuarioAdapter by lazy {
        pUsuarioAdapter()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)

        binding = ActivityClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(global.rolGlobal=="0")
        {
            traerDatosCliente();
        }else {
            traerDatos();
        }
        cargarAdaptador()
    }

    fun cargarAdaptador(){
        binding.listClientes.adapter = adapter
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

        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            var te = JSONObject.getString("telefono").toString()
            var id = JSONObject.getString("id").toString()
            var em = JSONObject.getString("email").toString()
            /*var ro = "Rol: Cliente"
            if(JSONObject.getString("rol").toString()=="1"){
                ro = "Rol: Administrador"
            }*/
            var ro = JSONObject.getString("rol").toString()

            var no = JSONObject.getString("nombre").toString()
            var ap = JSONObject.getString("apellido").toString()
            var co = JSONObject.getString("contrasena").toString()

            this.listData = clienteData(id,no,ap,te,em,ro,co)
            this.dataArrayList.add(this.listData)
            x++
        }
        println(this.dataArrayList)
        var listMenuData1: List<clienteData> = dataArrayList.toList() as List<clienteData>
        adapter.updateListPets(listMenuData1)
        //this.listAdapter = clienteAdapter(this@ClientesActivity,this.dataArrayList)
        //this.lista.adapter = this.listAdapter
        //this.lista.isClickable = true




    }


}