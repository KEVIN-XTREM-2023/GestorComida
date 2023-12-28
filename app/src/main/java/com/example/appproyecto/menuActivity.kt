package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appproyecto.adapter.pClienteAdapter
import com.example.appproyecto.data.menuData
import com.example.appproyecto.databinding.ActivityMenuBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import java.util.concurrent.Executors

class menuActivity : AppCompatActivity() {

    lateinit var lista : ListView;
    lateinit var arayLista : ArrayList<HashMap<String,String>>;

    var URL: String="http://10.79.7.28:8082/comida/menu/lista.php";


    private lateinit var binding: ActivityMenuBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: menuData
    var dataArrayList = ArrayList<menuData?>()
    lateinit var botonRegistrar : FloatingActionButton;

    private val adapter : pClienteAdapter by lazy {
        pClienteAdapter()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu)
        //this.botonRegistrar = findViewById(R.id.btnRegistrarMenu)
        //lista = findViewById<ListView>(R.id.listMenu)

        binding = ActivityMenuBinding.inflate(layoutInflater)

        if(global.rolGlobal=="0"){

            binding.btnRegistrarMenu.visibility= View.INVISIBLE
        }

        setContentView(binding.root)

        registrar();
        traerDatos();
        //inicializar()

        cargarAdaptador()
    }


    fun registrar(){
        binding.btnRegistrarMenu.setOnClickListener {
            global.proceso=1
            startActivity(Intent(this,registrarMenuActivity::class.java))
        }
    }

    fun cargarAdaptador(){
        binding.listMenu.adapter = adapter
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

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray(jsonString)


        var x=0;
        var listMenuData: List<menuData>
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            var pr = JSONObject.getString("precio").toString()
            var id = JSONObject.getString("id").toString()
            var de = JSONObject.getString("detalle").toString()
            var no = JSONObject.getString("nombre").toString()

            this.listData = menuData(id,no,de,pr)
            this.dataArrayList.add(this.listData)

            x++
        }
        println(this.dataArrayList)
        var listMenuData1: List<menuData> = dataArrayList.toList() as List<menuData>
        adapter.updateListPets(listMenuData1)
        //this.listAdapter = menuAdapter(this@menuActivity,this.dataArrayList)
        //this.lista.adapter = this.listAdapter
        //this.lista.isClickable = true




    }





}