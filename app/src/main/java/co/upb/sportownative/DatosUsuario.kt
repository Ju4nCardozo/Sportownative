package co.upb.sportownative

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_usuario.*

class DatosUsuario : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_usuario)

        //setup
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
        saveDatosUsuario(email ?:"")
        infoUsuario(email ?:"")
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
        }
    }

   private fun infoUsuario(email: String){

       buttonActualizar.setOnClickListener{
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

               editTextNombreCompleto.setText(nombrecompleto)
               editTextEdad.setText(edad.toString() + " años")
               editTextPeso.setText(peso.toString() + " Kg")
               editTextAltura.setText(altura.toString() + " cm")
               checkBoxCardiaco.isChecked = cardiaco
               checkBoxAsma.isChecked = asma
               checkBoxHipertension.isChecked = hipertension
               checkBoxDiabetes.isChecked = diabetes
               checkBoxCancer.isChecked = cancer
               checkBoxEpilepsia.isChecked = epilepsia
           }
       }
   }

    private fun setup(email: String, nombrecompleto: String, edad: Int, peso: Int, altura: Int, cardiaco: Boolean, asma: Boolean, hipertension: Boolean, diabetes: Boolean, cancer: Boolean, epilepsia: Boolean){

        title = "Datos Usuario"
        editTextNombreCompleto.setText(nombrecompleto)
        editTextEdad.setText(edad.toString() + " años")
        editTextPeso.setText(peso.toString() + " Kg")
        editTextAltura.setText(altura.toString() + " cm")
        checkBoxCardiaco.isChecked = cardiaco
        checkBoxAsma.isChecked = asma
        checkBoxHipertension.isChecked = hipertension
        checkBoxDiabetes.isChecked = diabetes
        checkBoxCancer.isChecked = cancer
        checkBoxEpilepsia.isChecked = epilepsia
    }
}