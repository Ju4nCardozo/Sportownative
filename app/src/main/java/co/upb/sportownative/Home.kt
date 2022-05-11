package co.upb.sportownative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.upb.sportownative.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

enum class ProviderType{
    BASIC,
    GOOGLE
}

private val db = FirebaseFirestore.getInstance()

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")
    }

    private fun setup(email: String){

        title = "Inicio"

        rutinasButton.setOnClickListener{
            val rutinasIntent = Intent(this, Rutinas::class.java).apply {
                putExtra("email", email)
            }
            startActivity(rutinasIntent)
        }

        dietasButton.setOnClickListener{
            val dietasIntent = Intent(this, Dietas::class.java).apply {
                putExtra("email", email)
            }
            startActivity(dietasIntent)
        }

        foto.setOnClickListener{
            val rutinasIntent = Intent(this, PerfilUsuario::class.java).apply {
                putExtra("email", email)
            }
            startActivity(rutinasIntent)
        }

        calidadAireButton.setOnClickListener{
            val calidadAireIntent = Intent(this, CalidadAire::class.java).apply {
                putExtra("email", email)
            }
            startActivity(calidadAireIntent)
        }

        db.collection("users").document(email).get().addOnSuccessListener {

            textViewUser.setText(it.get("nombre_completo") as String?)
        }
    }
}