package co.upb.sportownative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.upb.sportownative.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

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

        //Traer datos del shared preference
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val nombreusuario = prefs.getString("nombrecompleto", null).toString()
        textViewUser.setText(nombreusuario)

        //Recordar borrar datos al cerrar sesión
    }

    private fun setup(email: String){

        title = "Inicio"

        rutinasButton.setOnClickListener{
            val rutinasIntent = Intent(this, Rutinas::class.java).apply {
                putExtra("email", email)
            }
            startActivity(rutinasIntent)
        }

        /*dietasButton.setOnClickListener{
            val dietasIntent = Intent(this, Dietas::class.java).apply {
                putExtra("email", email)
            }
            startActivity(dietasIntent)

        }*/
    }
}