package co.upb.sportownative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.upb.sportownative.R
import android.content.Intent
import android.os.Handler
import co.upb.sportownative.Login

class bienvenido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)
        Handler().postDelayed({
            val intent = Intent(this@bienvenido, Login::class.java)
            startActivity(intent)
        }, 3000)
    }
}