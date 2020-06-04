package com.essa.curso.jetpack.lifecycle.ui.nombre

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.essa.curso.jetpack.lifecycle.R
import kotlinx.android.synthetic.main.nombre_fragment.*

class NombreFragment : Fragment() {

    companion object {
        fun newInstance() = NombreFragment()
    }

    //SE CREA LA VARIABLE PEREZOSA DE NOMBRE viewMODEL
    private lateinit var viewModel: NombreFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //CUANDO SE CREA LA VISTA SE ENLAZA ESTA CLASE CON EL LAYOUT CORRESPONDIENTE
        return inflater.inflate(  R.layout.nombre_fragment,
                                    container,
                                    false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //CUANDO LA VISTA YA ESTA CREADA SE ENLAZA LA PROPIEDAD VIEWMODEL CON LA CLASE VIEWMODEL
        viewModel = ViewModelProvider(this).get(NombreFragmentViewModel::class.java)
        //SE LLAMA AL METODO QUE NOS SUSCRIBE A LOS CAMBIOS
        suscribeChanges()
    }

    private fun suscribeChanges(){
        //SE AÑADE UN LISTENER AL CAMPO DE ENTRADA DE TEXTO
        nombreET.addTextChangedListener(
            //ESTE RECIBE UNA TAREA A EJECUTAR CUANDO SE DISPARA ALGUNO DE LOS EVENTOS YA DEFINIDOS
            object: TextWatcher {
                //DESPUES DE OCURRIR UN CAMBIO EN LA PALABRA POR PARTE DEL USUARIO
                //VAMOS CAMBIANDO EL VALOR DE LA VARIABLE MUTABLE LIVEDATA
                override fun afterTextChanged(s: Editable?) {
                    viewModel.nombreLiveData.postValue(s.toString())
                }
                //SI NO SE VAN A USAR LOS METODOS ES IMPORTANTES DEJAR LAS LLAVES JUNTAS, YA QUE ESTO
                //VA A INDICAR QUE EL METODO ESTA VACIO
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }
        )

        //SE VALIDA QUE EL OBTENER REFERENCIA A ESTA ACTIVIDAD NO OBTENGAMOS UN NULL
        this.activity?.let {
            //AHORA LE ASIGNAMOS UN OBSERVADOR A LA VARIABLE MUTABLE
            viewModel.nombreLiveData.observe(it, Observer<String>{ value ->
                //ESTE OBSERVADOR RECIBE EL VALOR Y SE LO ASIGNA AL TEXTVIEW EN SU PROPIEDAD DE TEXTO
               value?.let {
                   nombreTV.text = it
               }
            })
        }

    }

}
