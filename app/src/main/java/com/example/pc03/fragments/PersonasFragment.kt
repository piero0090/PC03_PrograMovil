package com.example.pc03.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pc03.Constantes
import com.example.pc03.R
import com.example.pc03.adapters.ListadoPersonasAdapter
import com.example.pc03.gestorPersonas
import com.example.pc03.models.Personas
import kotlinx.coroutines.*


class PersonasFragment: Fragment() {

    private lateinit var mRviPersonas: RecyclerView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            activity?.title = "Personas"
            setHasOptionsMenu(true)
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_personas, container, false)
        }

       /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            super.onCreateOptionsMenu(menu, inflater)
            inflater.inflate(R.menu.menu_planetas, menu)
        }*/

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            mRviPersonas = view.findViewById(R.id.rviPersonas)

            //val listaPlanetas : List<Planeta> = GestorPlanetas().obtenerListaPlanetas()
            /*GestorPlanetas().obtenerListaPlanetas {
                val adapter = ListadoPlanetasAdapter(it) {
                    Log.i("PlanetasFragment","Se hizo click en el planeta " + it.nombre);
                }
                mRviPlanetas.adapter = adapter
            }*/
            val gestor = gestorPersonas()

            /*GlobalScope.launch(Dispatchers.IO) {
                // Defecto : Default -> Tareas de alto costo computacional
                // IO : -> Tareas que no tinen costo alto pero tienen paradas
                val lista = gestor.obtenerListaPlanetasCorutinas()

                withContext(Dispatchers.Main) {
                    val adapter = ListadoPlanetasAdapter(lista) {
                        Log.i("PlanetasFragment","Se hizo click en el planeta " + it.nombre);
                    }
                    mRviPlanetas.adapter = adapter
                }
            }*/

            val sp = requireActivity().getSharedPreferences(
                Constantes.NOMBRE_SP, Context.MODE_PRIVATE)

            GlobalScope.launch(Dispatchers.Main) {
                val estaSincronizado = sp.getBoolean(Constantes.SP_ESTA_SINCRONIZADO,
                    false)
                var lista : List<Personas> = mutableListOf()
                if (!estaSincronizado) {
                    // Obtenemos la data del servicio externo
                    lista = withContext(Dispatchers.IO) {
                        gestor.obtenerListaPersonasRoom()
                    }

                    // Guardamos los planetas obtenidos en el servicio en Room
                    Log.d("PersonasFragment", lista.size.toString())
                    gestor.guardarListPersonasRoom(
                        requireActivity().applicationContext,
                        lista
                    )

                    /*gestor.guardarListaPlanetasFirebase(lista, {
                        // Caso exito
                        sp.edit().putBoolean(
                            Constantes.SP_ESTA_SINCRONIZADO, true).commit()
                        cargarListaPlanetas(lista)
                    }){
                        // Caso error guardado Firebase
                        Toast.makeText(requireActivity(),
                            "Error: ${it}", Toast.LENGTH_SHORT).show()
                    }*/
                } else {
                    // Obtenemos la data de Room (base de datos interna)
                    Log.d("PersonasFragment", "Room")
                    lista = gestor.obtenerListaPersonasRoom(
                        requireContext().applicationContext)
                    cargarListaPersonas(lista)
                }
            }
        }

        private fun cargarListaPersonas(lista: List<Personas>) {
            val adapter = ListadoPersonasAdapter(lista) {
               // Log.i("Se hizo click en " + it.nombre);
            }
            mRviPersonas.adapter = adapter
        }
    }


}