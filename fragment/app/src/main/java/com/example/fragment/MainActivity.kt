package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragment.fragment.HomeFragment
import androidx.window.layout.WindowMetricsCalculator.Companion as WindowMetricsCalculator1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val metrics = WindowMetricsCalculator1.getOrCreate()
            .computeCurrentWindowMetrics(this)

        val widthDp = metrics.bounds.width() /
                resources.displayMetrics.density

        if(widthDp>600f){
            supportFragmentManager.beginTransaction().add(R.id.tabLayout, HomeFragment()).commit()
        }else{
            supportFragmentManager.beginTransaction().add(R.id.mainLayout, HomeFragment()).commit()
        }

    }
}