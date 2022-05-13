package co.upb.sportownative

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*


private val db = FirebaseFirestore.getInstance()

class Rutina1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina1)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")
    }

    private fun setup(email: String) {

        title = "Rutina"


        db.collection("users").document(email).get().addOnSuccessListener {

            textViewUser.setText(it.get("nombre_completo") as String?)
        }
    }
}