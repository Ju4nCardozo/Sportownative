package co.upb.sportownative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.upb.sportownative.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        title = "Olvidé mi contraseña"

        val bundle = intent.extras
        val email = bundle?.getString("email")

        EnviarButtonForgot.setOnClickListener{
            val email = editTextEmailRegistroForgot.text.toString().trim(){ it <= ' '}
            if(email.isEmpty()){
                Toast.makeText(this@ForgotPassword,
                "introduzca su correo electrónico",
                Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this@ForgotPassword,
                        "El correo electrónico de recuperación fue enviado",
                        Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(
                            this@ForgotPassword,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}