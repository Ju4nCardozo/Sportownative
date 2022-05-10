package co.upb.sportownative

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class Dietas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietas)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")

        //Traer datos del shared preference
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val nombreusuario = prefs.getString("nombrecompleto", null).toString()
        textViewUser.setText(nombreusuario)


        //Recordar borrar datos al cerrar sesión
    }
    private fun setup(email: String){

        title = "Inicio"



    }
}