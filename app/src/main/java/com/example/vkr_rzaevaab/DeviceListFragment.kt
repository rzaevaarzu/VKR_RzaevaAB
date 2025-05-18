package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView



class DeviceListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeviceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device_list, container, false)

        // Найти RecyclerView
        recyclerView = view.findViewById(R.id.device_list)

        // Пример списка
        val items = listOf(
            DeviceItem(R.drawable.device, "Монитор 1"),
            DeviceItem(R.drawable.device, "Монитор 2"),
            DeviceItem(R.drawable.device, "Монитор 3"),
            DeviceItem(R.drawable.device, "Монитор 3"),
            DeviceItem(R.drawable.device, "Монитор 3"),
            DeviceItem(R.drawable.device, "Монитор 3"),
            DeviceItem(R.drawable.device, "Монитор 3"),
            DeviceItem(R.drawable.device, "Монитор 3"),
            DeviceItem(R.drawable.device, "Монитор 4")
        )

        // Настройка RecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.addItemDecoration(SpacingItemDecoration(4))
        recyclerView.adapter = DeviceAdapter(items)

        return view
    }
}