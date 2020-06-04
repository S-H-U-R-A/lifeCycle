package com.essa.curso.jetpack.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.essa.curso.jetpack.lifecycle.ui.nombre.NombreFragment

class NombreFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nombre_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NombreFragment.newInstance())
                .commitNow()
        }
    }
}
