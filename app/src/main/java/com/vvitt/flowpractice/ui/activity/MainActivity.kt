package com.vvitt.flowpractice.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vvitt.flowpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}