package com.essa.curso.jetpack.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_cronometro.*

/*
    PARA EMPEZAR A TRABAJAR CON VIEW MODEL Y LIFECYCLE, LO PRIMERO QUE SE DEBE HACER ES IMPLEMENTAR
    LA LIBRERIA EN EL BUILD GRADLE DE LA APP, LUEGO SE CREA LA CLASE VIEW MODEL QUE FUNCIONARA CON
    LA REFERENCIA EN EL ACTIVITY QUE SE USE.

    EMPEZANDO EN EL ACTIVITY LO PRIMERO ES CREAR UNA VARIABLE DE TIPO VIEWMODEL DE LA QUE QUERAMOS USAR
    SEGUIDO DE EN EL METODO CREATE DE LA APP, LO ASIGNAMOS; ENLAZANDO LA VARIABLE CON LA CLASE QUE REPRESENTA AL VIEWMODEL
    ESTO MEDIANTE EL USO DE VIEWMODELPROVIDER EL CUAL HACE DUCHO ENLACE
*/

class CronometroActivity : AppCompatActivity() {

    private lateinit var  cronometroViewModel: CronometroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronometro)
        //ESTO NO SE DEBERIA INICIAR ACA, PORQUE ES UN DATO QUE QUEREMOS CONSERVAR
        //cronometro_view.start()

        //A LA VARIABLE DE TIPO VIEW MODEL SE LE ENLAZA CUAL ES SU VIEW MODEL CORRESPONDIENTE
        cronometroViewModel = ViewModelProvider(this).get(CronometroViewModel::class.java)

        //SE INVOCA LA FUNCION QUE ARRANCA EL CRONOMETRO
        //usoViewModel()

        //PRUEBA USANDO OTRO VIEWMODEL OBSERVABLE
        suscribirnos()

    }

    /*
        FUNCIÓN QUE VALIDA SI LA VARIABLE DEL VIEWMODEL ESTA EN CERO PARA
        SABER SI SE ASIGNA COMO VALOR INICIAL EL VALOR QUE YA TIENE LA VARIABLE O APENAS COMIENZA
    */
    private  fun usoViewModel(){
        //SE VALIDA SI EL CRONOMETRO ESTA EN CERO O NO
        if(cronometroViewModel.startTime == 0L){
            //DE SER ASI SE  EL VIEW MODEL NO TIENE DATA Y SE CREA EL TIEMPO PARA COMENZAR
            val startTime = SystemClock.elapsedRealtime()
            //A LA VARIABLE EN EL VIEW MODEL SE LE SETEA EL VALOR
            cronometroViewModel.startTime = startTime
            //Y A LA PROPIEDAD DEL XML/UI SE LE ASIGNA DICHO VALOR
            cronometro_view.base = startTime
        }else{
            //SI ENTRÓ POR ACA, ES QUE CRONOMETRO YA TIENEN UN VALOR Y ESTA ALMACENADO EN LA
            //VARIABLE DEL VIEW MODEL
            cronometro_view.base = cronometroViewModel.startTime
        }

        //SE INICIA EL CRONOMETRO
        cronometro_view.start();

    }

    //FUNCION DE EJEMPLO DE VIEWMODEL CON OBSERVABLE
    private fun suscribirnos(){
        //VARIABLE OBSERVABLE QUE VA A EJECUTAR LA ACCION DE OBTENER LA PLANTILLA DEL ARCHIVO STRING
        //Y LE VA A ASIGANAR EL VALOR DE LOS SEGUNDOS DEVUELTO POR EL OBSERVABLE
        //PARA LUEGO SER REPRESENTADO EN EL TEXT DEL CRONOMETRO
        val tiempoTrascurridoObserver = Observer<Long>{ aLong ->
            val nuevoTexto = this@CronometroActivity.resources.getString(R.string.segundos, aLong)
            cronometro_view.text = nuevoTexto
        }
        //SE OBSERVA LA PROPIEDAD tiempoTrascurrido DEL VIEWMODEL QUE ES UN MUTABLELIVEDATA QUE RETORNA
        //UN VALOR CADA SEGUNDO
        cronometroViewModel.tiempoTrascurrido.observe(this, tiempoTrascurridoObserver)
    }

}
