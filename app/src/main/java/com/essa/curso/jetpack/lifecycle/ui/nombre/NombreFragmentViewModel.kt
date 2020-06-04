package com.essa.curso.jetpack.lifecycle.ui.nombre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//CLASE VIEW MODEL
class NombreFragmentViewModel : ViewModel() {
    //VARIABLE DE TIPO LIVEDATA QUE PERMITE CAMBIOS ES DECIR MUTABLE
    var nombreLiveData = MutableLiveData<String>()
}
