package co.upb.sportownative

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        title = "Registro"

        //setup
        setup()
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showDatosUsuario(email: String){

        val datosUsuarioIntent = Intent(this, DatosUsuario::class.java).apply {
            putExtra("email", email)
        }
        startActivity(datosUsuarioIntent)
    }

    private fun setup(){
        RegistroButton.setOnClickListener{
            if(editTextEmailRegistro.text.isNotEmpty() && editTextEmailConfirmar.text.isNotEmpty() && editTextPasswordRegistro.text.isNotEmpty()){
                if(editTextEmailRegistro.text.toString() == editTextEmailConfirmar.text.toString()){
                    if(editTextPasswordRegistro.length() > 8 && editTextPasswordRegistro.text.toString() == editTextConfirmarPasswordRegistro.text.toString()){
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmailRegistro.text.toString(), editTextPasswordRegistro.text.toString()).addOnCompleteListener{
                            if(it.isSuccessful){
                                db.collection("users").document(editTextEmailRegistro.text.toString())
                                showDatosUsuario(it.result?.user?.email ?:"")
                            }else{
                                showAlert()
                            }
                        }
                    }else{
                        Toast.makeText(this, "La contraseña debe de ser mayor a 8 caracteres", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Los dos email no coinciden", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Debe de completar los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}