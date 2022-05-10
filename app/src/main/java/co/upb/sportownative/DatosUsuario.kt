package co.upb.sportownative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_usuario.*

private val db = FirebaseFirestore.getInstance()

class DatosUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_usuario)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        saveDatosUsuario(email ?:"")
    }

    private fun showHome(email: String){

        val homeIntent = Intent(this, Home::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    private fun saveDatosUsuario(email: String){
        buttonGuardar.setOnClickListener{
            db.collection("users").document(email).set(
                hashMapOf("nombre_completo" to editTextNombreCompleto.text.toString(),
                "edad" to editTextEdad.text.toString().toInt(),
                "peso" to editTextPeso.text.toString().toInt(),
                "altura" to editTextAltura.text.toString().toInt(),
                "cardiaco" to checkBoxCardiaco.isChecked.toString().toBoolean(),
                "asma" to checkBoxHipertension.isChecked.toString().toBoolean(),
                "hipertension" to checkBoxHipertension.isChecked.toString().toBoolean(),
                "diabetes" to checkBoxDiabetes.isChecked.toString().toBoolean(),
                "cancer" to checkBoxCancer.isChecked.toString().toBoolean(),
                "epilepsia" to checkBoxEpilepsia.isChecked.toString().toBoolean())
            )

            showHome(email)
        }
    }
}