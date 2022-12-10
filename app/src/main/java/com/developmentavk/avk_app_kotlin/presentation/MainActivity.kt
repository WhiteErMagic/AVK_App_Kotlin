package com.developmentavk.avk_app_kotlin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.developmentavk.avk_app_kotlin.databinding.ActivityMainBinding
import com.developmentavk.avk_app_kotlin.domain.CheckPermission
import com.developmentavk.avk_app_kotlin.domain.Navigator
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.CulcAction
import com.developmentavk.avk_app_kotlin.presentation.main.MainWindowFragment


class MainActivity : AppCompatActivity(), Navigator, CulcAction {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CheckPermission.checkPermission(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        CheckPermission.checkPermission(this)
    }

    override fun goToNext(fragment: Fragment) {
        if(fragment::class.java.name == MainWindowFragment::class.java.name) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
                .commit()
        }else {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun goToBack() {
        super.onBackPressed()
    }

    override fun addNumber(arg: String) {
        supportFragmentManager.setFragmentResult("addNumber", bundleOf("addNumber" to arg))
    }

    override fun removeNumber() {
        supportFragmentManager.setFragmentResult("removeNumber", bundleOf("removeNumber" to true))
    }
}