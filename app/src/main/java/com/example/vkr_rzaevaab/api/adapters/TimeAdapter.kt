package com.example.vkr_rzaevaab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.api.viewmodels.DeviceReservViewModel
import androidx.fragment.app.viewModels


class TimeAdapter(
    private val items: List<String>,
    private val blockedTimes: List<String?>
) : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {


    var onItemClick : ((String) -> Unit)? = null

    private var selectedTime: String? = null

    fun getSelectedTime(): String? = selectedTime

    class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.time_item, parent, false)
        return TimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val item = items[position]
        holder.time.text=item

        holder.time.isEnabled = true // По умолчанию доступна
        if (item in blockedTimes) {holder.time.isEnabled = false}
        holder.time.isSelected = (item == selectedTime)

        holder.time.setOnClickListener{
            selectedTime = item
            notifyDataSetChanged()
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
