package co.upb.sportownative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_usuario.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_perfil_usuario.*

private val db = FirebaseFirestore.getInstance()
private var edad = 0
private var peso = 0
private var altura = 0

class PerfilUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        val bundle = intent.extras
        val email = bundle?.getString("email")

        setup(email ?:"")
        infoUsuario(email ?:"")
    }

    private fun infoUsuario(email: String){

        buttonActualizarPerfil.setOnClickListener{

            edad = editTextEdadPerfil.text.toString().toInt()

            peso = editTextPesoPerfil.text.toString().toInt()

            altura = editTextAlturaPerfil.text.toString().toInt()

            db.collection("users").document(email).update(mapOf("nombre_completo" to editTextNombreCompletoPerfil.text.toString(),
                    "edad" to edad,
                    "peso" to peso,
                    "altura" to altura,
                    "cardiaco" to checkBoxCardiacoPerfil.isChecked.toString().toBoolean(),
                    "asma" to checkBoxAsmaPerfil.isChecked.toString().toBoolean(),
                    "hipertension" to checkBoxHipertensionPerfil.isChecked.toString().toBoolean(),
                    "diabetes" to checkBoxDiabetesPerfil.isChecked.toString().toBoolean(),
                    "cancer" to checkBoxCancerPerfil.isChecked.toString().toBoolean(),
                    "epilepsia" to checkBoxEpilepsiaPerfil.isChecked.toString().toBoolean()
            )).addOnCompleteListener { val homeIntent = Intent(this, Home::class.java)
                startActivity(homeIntent) }

       }
   }

    private fun setup(email: String){

        title = "Datos Usuario"

        db.collection("users").document(email).get().addOnSuccessListener {

            editTextNombreCompletoPerfil.setText(it.get("nombre_completo") as String?)
            editTextEdadPerfil.setText(it.get("edad").toString())
            editTextPesoPerfil.setText(it.get("peso").toString())
            editTextAlturaPerfil.setText(it.get("altura").toString())
            checkBoxCardiacoPerfil.isChecked = it.get("cardiaco").toString().toBoolean()
            checkBoxAsmaPerfil.isChecked = it.get("asma").toString().toBoolean()
            checkBoxHipertensionPerfil.isChecked = it.get("hipertension").toString().toBoolean()
            checkBoxDiabetesPerfil.isChecked = it.get("diabetes").toString().toBoolean()
            checkBoxCancerPerfil.isChecked = it.get("cancer").toString().toBoolean()
            checkBoxEpilepsiaPerfil.isChecked = it.get("epilepsia").toString().toBoolean()

        }

        buttonCerrarSession.setOnClickListener{
            val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            val homeIntent = Intent(this, Login::class.java)
            startActivity(homeIntent)
        }
    }
}