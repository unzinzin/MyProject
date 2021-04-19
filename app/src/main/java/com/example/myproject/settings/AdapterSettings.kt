package com.example.myproject.settings


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R

class AdapterSettings(private val parametreList: Array<SettingsRecyclerView.SettingsItem>, private val onClick: ((selectedDevice: String) -> Unit)? = null) : RecyclerView.Adapter<AdapterSettings.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun showItem(item: SettingsRecyclerView.SettingsItem){
            itemView.findViewById<TextView>(R.id.textItem).text = item.name
            itemView.findViewById<TextView>(R.id.textItem).setOnClickListener({
                item.onClick.invoke()
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parametre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(parametreList[position])
    }

    override fun getItemCount(): Int {
        return parametreList.size
    }

}