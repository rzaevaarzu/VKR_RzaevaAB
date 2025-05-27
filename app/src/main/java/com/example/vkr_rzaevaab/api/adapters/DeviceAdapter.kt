package com.example.vkr_rzaevaab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.DeviceItem

class DeviceAdapter(
    private val items: List<Device>
) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    var onItemClick : ((Device) -> Unit)? = null

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val title: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val item = items[position]
        holder.icon
        holder.title.text = item.name
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
