package com.example.pc03.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pc03.R
import com.example.pc03.gestorPersonas
import com.example.pc03.models.Personas

class ListadoPersonasAdapter (private val mListaPersonas : List<Personas>):
     RecyclerView.Adapter<ListadoPersonasAdapter.ViewHolder>(){
        class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val tviPersonaDepartamento : TextView
            val tviCantidadDepa : TextView


            init {
                tviPersonaDepartamento = view.findViewById(R.id.tviPersonaDepartamento)
                tviCantidadDepa= view.findViewById(R.id.tviPersonasCantidad)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_persona, parent, false)
            val viewHolder = ViewHolder(view)
            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val personas = mListaPersonas [position]
            holder.tviPersonaDepartamento.text = personas.departamento
            holder.tviCantidadDepa.text = personas.cantidad.toString()
        }

        override fun getItemCount(): Int {
            // Devolver el nro de items a mostrar
            return mListaPersonas.size


        }
}