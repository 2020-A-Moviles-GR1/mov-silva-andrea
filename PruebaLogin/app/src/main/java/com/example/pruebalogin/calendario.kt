package com.example.pruebalogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.INVISIBLE
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class calendario : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

    }

    override fun onClick(v: View?) {


        val k= Intent(this, despueCalendario::class.java);



        startActivity(k);
    }

}