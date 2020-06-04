package com.essa.curso.jetpack.lifecycle

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CronometroViewModel : ViewModel() {
    //VARIABLE DONDE SE GUARDA EL DATO DEL CRONOMETRO EL TIEMPO
    //QUE LA APLICACIÓN ESTE ACTIVA
    var startTime: Long = 0L

    //VARIABLE MUTABLELIVEDATA QUE VA SER OBSERVADA; PARA CADA CAMBIO
    //LA DIFERENCA ENTRE LIVEDATA Y MUTABLELIVEDATA ES QUE LA PRIMERA NO PUEDE CAMBIAR AUNQUE SE PUEDE OBSERVAR
    //Y LA SEGUNDA SI PUEDE CAMBIAR Y SE PUEDE OBSERVAR
    val tiempoTrascurrido = MutableLiveData<Long>()

    //VARIABLE SEG QUE REPRESENTA UN SEGUNDO EN MILISEGUNDOS
    private val SEG: Long = 1000L
    //VARIABLE QUE REPRESENTA EL TIEMPO INICIAL EN EL QUE SE INICIA EL CRONOMETRO
    private var tiempoInicial: Long = 0


    //CONSTRUCTOR
    init {
        //SE OBTIENE DEL SISTEMA EL TIEMPO EL MILISEGUNDOS
        tiempoInicial = SystemClock.elapsedRealtime()

        //VARIABLE DE TIPO TIEMPO
        val timer = Timer()

        //A LA VARIABLE timer SE LE CREA UNA TAREA QUE VA A REPETIR CONSTANTEMENTE
        //ESTA FUNCION RECIBE COMO PARAMETROS UNA TAREA, UN RETRASO ANTES DE QUE SE EJECUTE LA TAREA O DELAY
        //Y RECIBE EL TIEMPO PROMEDIO ENTRE CADA EJECUCION DE LA TAREA
        timer.scheduleAtFixedRate(object: TimerTask(){
            override fun run() {
                //SE OBTIENE EL VALOR TRASCURRIDO  ENTRE EL TIEMPO INICIAL Y EL ACTUAL Y AL DIVIDIRLO
                //EN 1000 OBTENEMOS LOS SEGUNDOS
                val newValue = (SystemClock.elapsedRealtime() - tiempoInicial) / 1000
                //POR ULTIMO SE RETORNA EL VALOR OBTENIDO , ACTUALIZANDO  DE ESTA FORMA LA VARIABLE
                // QUE EN ULTMA INSTANCIA VA A SER OBSERVADA
                tiempoTrascurrido.postValue(newValue)
            }
        }, SEG, SEG)

    }


}