package co.upb.sportownative

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.GoogleAuthException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_datos_usuario.*
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private val GOOGLE_SING_IN = 100
    private val db = FirebaseFirestore.getInstance()
    private var nombrecompleto = "bobohpta"
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
        setContentView(R.layout.activity_login)


        //setup
        setup()
        session()
    }

    override fun onStart(){
        super.onStart()
        authLayout.visibility = View.VISIBLE
    }

    private fun setup() {
        title = "Login"

        loginButton.setOnClickListener{
            if(emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener{ it ->
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?:"")
                        //showHome2(it.result?.user?.email ?:"")
                    }else{
                        showAlert()
                    }
                }
            }
        }

        olvidasteContra.setOnClickListener{
            val intent = Intent(this@Login, ForgotPassword::class.java)
            startActivity(intent)
        }

        registrarse.setOnClickListener{
            val intent = Intent(this@Login, Registro::class.java)
            startActivity(intent)
        }

        googleButton.setOnClickListener{

            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("562685779526-ak229eh106vr9a3t5v2ijnkmbv59irap.apps.googleusercontent.com")
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /*private fun showHome(email: String, nombrecompleto: String, edad: Int, peso: Int, altura: Int,
    cardiaco: Boolean, asma: Boolean, hipertension: Boolean, diabetes: Boolean, cancer: Boolean, epilepsia: Boolean){

        val homeIntent = Intent(this, DatosUsuario::class.java).apply {
            putExtra("email", email)
            putExtra("nombrecompleto", nombrecompleto)
            putExtra("edad", edad)
            putExtra("peso", peso)
            putExtra("altura", altura)
            putExtra("cardiaco", cardiaco)
            putExtra("asma", asma)
            putExtra("hipertension", hipertension)
            putExtra("diabetes", diabetes)
            putExtra("cancer", cancer)
            putExtra("epilepsia", epilepsia)
        }
        startActivity(homeIntent)
    }*/

    private fun showHome(email: String){

        val homeIntent = Intent(this, Home::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)

        if(email != null){
            authLayout.visibility = View.INVISIBLE
            showHome(email)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SING_IN){

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            try{
                if(account != null) {

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {

                        if (it.isSuccessful) {
                            showHome(account.email ?: "")
                            //showHome2(account.email ?: "")
                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e: ApiException){
                showAlert()
            }
        }
    }
}