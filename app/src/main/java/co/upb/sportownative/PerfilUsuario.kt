package co.upb.sportownative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_usuario.*
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
        nombrecompleto = bundle?.getString("nombrecompleto").toString()
        edad = bundle?.getInt("edad").toString().toInt()
        peso = bundle?.getInt("peso").toString().toInt()
        altura = bundle?.getInt("altura").toString().toInt()
        cardiaco = bundle?.getBoolean("cardiaco").toString().toBoolean()
        asma = bundle?.getBoolean("asma").toString().toBoolean()
        hipertension = bundle?.getBoolean("hipertension").toString().toBoolean()
        diabetes = bundle?.getBoolean("diabetes").toString().toBoolean()
        cancer = bundle?.getBoolean("cancer").toString().toBoolean()
        epilepsia = bundle?.getBoolean("epilepsia").toString().toBoolean()

        setup(email?:"", nombrecompleto, edad, peso, altura, cardiaco, asma, hipertension, diabetes, cancer, epilepsia)
        infoUsuario(email ?:"")
    }

    private fun infoUsuario(email: String){

        buttonActualizarPerfil.setOnClickListener{
           db.collection("users").document(email).get().addOnSuccessListener {

               nombrecompleto = it.getString("nombre_completo").toString()
               edad = it.get("edad").toString().toInt()
               peso = it.get("peso").toString().toInt()
               altura = it.get("altura").toString().toInt()
               cardiaco = it.get("cardiaco").toString().toBoolean()
               asma = it.get("asma").toString().toBoolean()
               hipertension = it.get("hipertension").toString().toBoolean()
               diabetes = it.get("diabetes").toString().toBoolean()
               cancer = it.get("cancer").toString().toBoolean()
               epilepsia = it.get("epilepsia").toString().toBoolean()

               editTextNombreCompletoPerfil.setText(nombrecompleto)
               editTextEdadPerfil.setText(edad.toString() + " años")
               editTextPesoPerfil.setText(peso.toString() + " Kg")
               editTextAlturaPerfil.setText(altura.toString() + " cm")
               checkBoxCardiacoPerfil.isChecked = cardiaco
               checkBoxAsmaPerfil.isChecked = asma
               checkBoxHipertensionPerfil.isChecked = hipertension
               checkBoxDiabetesPerfil.isChecked = diabetes
               checkBoxCancerPerfil.isChecked = cancer
               checkBoxEpilepsiaPerfil.isChecked = epilepsia

               val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
               prefs.putString("email", email)
               prefs.putInt("edad", edad)
               prefs.putInt("peso", peso)
               prefs.putInt("altura", altura)
               prefs.putBoolean("cardiaco", cardiaco)
               prefs.putBoolean("asma", asma)
               prefs.putBoolean("hipertension", hipertension)
               prefs.putBoolean("diabetes", diabetes)
               prefs.putBoolean("cancer", cancer)
               prefs.putBoolean("epilepsia", epilepsia)
               prefs.apply()
           }
       }
   }

    private fun setup(email: String, nombrecompleto: String, edad: Int, peso: Int, altura: Int, cardiaco: Boolean, asma: Boolean, hipertension: Boolean, diabetes: Boolean, cancer: Boolean, epilepsia: Boolean){

        title = "Datos Usuario"
        editTextNombreCompletoPerfil.setText(nombrecompleto)
        editTextEdadPerfil.setText(edad.toString() + " años")
        editTextPesoPerfil.setText(peso.toString() + " Kg")
        editTextAlturaPerfil.setText(altura.toString() + " cm")
        checkBoxCardiacoPerfil.isChecked = cardiaco
        checkBoxAsmaPerfil.isChecked = asma
        checkBoxHipertensionPerfil.isChecked = hipertension
        checkBoxDiabetesPerfil.isChecked = diabetes
        checkBoxCancerPerfil.isChecked = cancer
        checkBoxEpilepsiaPerfil.isChecked = epilepsia

        buttonAtras.setOnClickListener {
            val atrasIntent = Intent(this, PerfilUsuario::class.java)
            startActivity(atrasIntent)
        }
    }
}