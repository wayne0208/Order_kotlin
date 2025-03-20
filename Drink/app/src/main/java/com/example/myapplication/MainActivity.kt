package com.example.myapplication

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result: androidx.activity.result.ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val drink = intent?.getStringExtra("drink")
            val sugar = intent?.getStringExtra("sugar")
            val ice = intent?.getStringExtra("ice")
            val tvMeal = findViewById<TextView>(R.id.tvMeal)
            tvMeal.text = " 飲料：$drink\n\n 甜度：$sugar\n\n 冰塊：$ice"

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnChoice = findViewById<Button>(R.id.btnChoice)
        btnChoice.setOnClickListener{
            val intent = Intent(this, SecActivity::class.java)
            startForResult.launch(intent)
        }

    }
}