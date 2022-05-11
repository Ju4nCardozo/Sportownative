package co.upb.sportownative

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_dietas.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.textViewUser

private val db = FirebaseFirestore.getInstance()

class Dietas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietas)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")
    }
    private fun setup(email: String){

        title = "Dietas"
        db.collection("users").document(email).get().addOnSuccessListener {

            textViewUser.setText(it.get("nombre_completo") as String?)
        }
        fotoDietas.setOnClickListener{
            val rutinasIntent = Intent(this, PerfilUsuario::class.java).apply {
                putExtra("email", email)
            }
            startActivity(rutinasIntent)
        }
    }
}