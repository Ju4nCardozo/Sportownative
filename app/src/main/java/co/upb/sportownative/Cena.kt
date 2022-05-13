package co.upb.sportownative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*

private val db = FirebaseFirestore.getInstance()

class Cena : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cena)

        title = "Cena"
        val bundle = intent.extras
        val email = bundle?.getString("email")

        if (email != null) {
            db.collection("users").document(email).get().addOnSuccessListener {

                textViewUser.setText(it.get("nombre_completo") as String?)
            }
        }
    }
}