package co.upb.sportownative

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.upb.sportownative.R

enum class ProviderType{
    BASIC,
    GOOGLE
}

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")

        //Guardado de datos


        //Recordar borrar datos al cerrar sesión
    }

    private fun setup(email: String){

        title = "Inicio"

    }
}