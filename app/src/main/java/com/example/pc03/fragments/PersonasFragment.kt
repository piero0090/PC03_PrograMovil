package com.example.pc03.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pc03.Constantes
import com.example.pc03.R
import com.example.pc03.adapters.ListadoPersonasAdapter
import com.example.pc03.gestorPersonas
import com.example.pc03.models.Personas
import kotlinx.coroutines.*


class PersonasFragment: Fragment() {

    private var fechaBuscar: TextView? =null
    private lateinit var mRviPersonas: RecyclerView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //activity?.title = "Ver Data"
            setHasOptionsMenu(true)
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_personas, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            mRviPersonas = view.findViewById(R.id.rviPersonas)

            fechaBuscar = view.findViewById(R.id.txtFecha)
            val gestor = gestorPersonas()

            val sp = requireActivity().getSharedPreferences(
                Constantes.NOMBRE_SP, Context.MODE_PRIVATE)
/*
            GlobalScope.launch(Dispatchers.Main) {
                val estaSincronizado = sp.getBoolean(Constantes.SP_ESTA_SINCRONIZADO,
                    false)
                var lista : List<Personas> = mutableListOf()
                if (!estaSincronizado) {
                    lista = withContext(Dispatchers.IO) {
                        gestor.obtenerListaPersonasRoom(requireActivity().applicationContext)
                    }
                    gestor.guardarListPersonasRoom(
                        requireActivity().applicationContext,
                        lista
                    )
                    cargarListaPersonas(lista)

                } else {
                    lista = gestor.obtenerListaPersonasRoom(
                        requireContext().applicationContext)
                    cargarListaPersonas(lista)
                }

            }
*/
            val fecha = "20220110"
            val txtSinData = view.findViewById<TextView>(R.id.txtSinData)

            var lista : List<Personas> = mutableListOf()
            lista = gestor.obtenerListaPersonasRoom(
                requireContext().applicationContext, fecha)

            if(lista.isNotEmpty()){
                cargarListaPersonas(lista)
                }
            else{
                txtSinData?.setVisibility(TextView.VISIBLE)
            }

        }

        private fun cargarListaPersonas(lista: List<Personas>) {
            val adapter = ListadoPersonasAdapter(lista)
            mRviPersonas.adapter = adapter
        }
    }


