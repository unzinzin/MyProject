package com.example.myproject.historique
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R

class HistoriqueAdapter(private val historiqueList : Array<String>) : RecyclerView.Adapter<HistoriqueAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun displayItem(item : String){
            itemView.findViewById<TextView>(R.id.textItem).text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parametre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.displayItem(historiqueList[position])
    }

    override fun getItemCount(): Int {
        return historiqueList.size
    }

}