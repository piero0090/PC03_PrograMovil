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
            val tviPersonaUserID : TextView
            val tviPersonaFechaCorte: TextView

            init {
                tviPersonaDepartamento = view.findViewById(R.id.tviPersonaDepartamento)
                tviPersonaUserID= view.findViewById(R.id.tviPersonaUserID)
                tviPersonaFechaCorte= view.findViewById(R.id.tviPersonaFechaCorte)
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
            holder.tviPersonaUserID.text = personas.userid
            holder.tviPersonaFechaCorte.text =  personas.fecha_corte
        }

        override fun getItemCount(): Int {
            // Devolver el nro de items a mostrar
            return mListaPersonas.size


        }
}