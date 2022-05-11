package co.upb.sportownative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.upb.sportownative.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.textViewUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_rutinas.*

private val db = FirebaseFirestore.getInstance()

class Rutinas : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutinas)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")
    }
    private fun setup(email: String){

        title = "Rutinas"
        db.collection("users").document(email).get().addOnSuccessListener {

            textViewUser.setText(it.get("nombre_completo") as String?)
        }
        fotoRutinas.setOnClickListener{
            val rutinasIntent = Intent(this, PerfilUsuario::class.java).apply {
                putExtra("email", email)
            }
            startActivity(rutinasIntent)
        }
    }
}