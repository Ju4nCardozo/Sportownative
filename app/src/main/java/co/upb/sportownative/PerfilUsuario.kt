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
private var nombrecompleto = " "
private var edad = 0
private var peso = 0
private var altura = 0
private var cardiaco = false
private var asma = false
private var hipertension = false
private var diabetes = false
private var cancer = false
private var epilepsia = false

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

            var splitedad = editTextEdadPerfil.text.toString().split(" ")
            edad = splitedad[0].toInt()

            var splitpeso = editTextPesoPerfil.text.toString().split(" ")
            peso = splitpeso[0].toInt()

            var splitaltura = editTextAlturaPerfil.text.toString().split(" ")
            altura = splitedad[0].toInt()

            db.collection("users").document(email).set(
                hashMapOf("nombre_completo" to editTextNombreCompleto.text.toString(),
                    "edad" to edad,
                    "peso" to peso,
                    "altura" to altura,
                    "cardiaco" to checkBoxCardiaco.isChecked.toString().toBoolean(),
                    "asma" to checkBoxHipertension.isChecked.toString().toBoolean(),
                    "hipertension" to checkBoxHipertension.isChecked.toString().toBoolean(),
                    "diabetes" to checkBoxDiabetes.isChecked.toString().toBoolean(),
                    "cancer" to checkBoxCancer.isChecked.toString().toBoolean(),
                    "epilepsia" to checkBoxEpilepsia.isChecked.toString().toBoolean())
            )
       }
   }

    private fun setup(email: String){

        title = "Datos Usuario"

        db.collection("users").document(email).get().addOnSuccessListener {

            editTextNombreCompletoPerfil.setText(it.get("nombre_completo") as String?)
            editTextEdadPerfil.setText(it.get("edad").toString() + " años")
            editTextPesoPerfil.setText(it.get("peso").toString() + " Kg")
            editTextAlturaPerfil.setText(it.get("altura").toString() + " cm")
            checkBoxCardiacoPerfil.isChecked = it.get("cardiaco").toString().toBoolean()
            checkBoxAsmaPerfil.isChecked = it.get("asma").toString().toBoolean()
            checkBoxHipertensionPerfil.isChecked = it.get("hipertension").toString().toBoolean()
            checkBoxDiabetesPerfil.isChecked = it.get("diabetes").toString().toBoolean()
            checkBoxCancerPerfil.isChecked = it.get("cancer").toString().toBoolean()
            checkBoxEpilepsiaPerfil.isChecked = it.get("epilepsia").toString().toBoolean()

        }


        buttonAtras.setOnClickListener {
            val atrasIntent = Intent(this, PerfilUsuario::class.java)
            startActivity(atrasIntent)
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