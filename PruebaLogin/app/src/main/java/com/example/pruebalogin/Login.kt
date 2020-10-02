package com.example.pruebalogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.LogInCallback
import com.parse.ParseException;
import com.parse.ParseUser;


class Login : AppCompatActivity(), View.OnClickListener {
    lateinit var txtusuario:EditText
    lateinit var btnIngresar:Button
    lateinit var txtcontrasena:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

         btnIngresar=   findViewById(R.id.btningresar);

        txtusuario=  findViewById(R.id.txtusuario);
        txtcontrasena=  findViewById(R.id.txtcontrasena);
        btnIngresar.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        validarUsuario()
    }

    private fun validarUsuario() {
        ParseUser.logInInBackground(
            txtusuario.getText().toString(),
            txtcontrasena.getText().toString(),
            object : LogInCallback {
                override fun done(user: ParseUser?, e: ParseException) {
                    if (user != null) {
                        // Hooray! The user is logged in.
                        startActivity(Intent(this@Login, MainActivity::class.java))
                    } else {
                        // Signup failed. Look at the ParseException to see what happened.
                        Toast.makeText(
                            this@Login,
                            "Error! " + e.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }
}