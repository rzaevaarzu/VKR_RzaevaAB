package com.example.vkr_rzaevaab.api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.R
import com.example.vkr_rzaevaab.entities.Device
import com.example.vkr_rzaevaab.entities.Event
import com.example.vkr_rzaevaab.entities.EventItem

class EventAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var onItemClick : ((Event) -> Unit)? = null


    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.title.text = event.name
        holder.date.text = event.date
        holder.time.text = event.time
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(event)
        }
    }

    override fun getItemCount(): Int = events.size
}
