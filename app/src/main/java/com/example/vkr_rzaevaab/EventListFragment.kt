package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.api.adapters.EventAdapter
import com.example.vkr_rzaevaab.databinding.FragmentEventListBinding
import com.example.vkr_rzaevaab.entities.Event

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EventListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    private var _binding: FragmentEventListBinding? = null
    private val binding: FragmentEventListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_list, container, false)

        // Найти RecyclerView
        recyclerView = view.findViewById(R.id.eventList)

        // Пример списка
        val items = listOf(
            Event("ГОЙДА", "ДАТА ГОЙДЫ", "ВРЕМЯ ГОЙДЫ"),
            Event("ГОЙДА", "ДАТА ГОЙДЫ", "ВРЕМЯ ГОЙДЫ"),
            Event("ГОЙДА", "ДАТА ГОЙДЫ", "ВРЕМЯ ГОЙДЫ"),
            Event("ГОЙДА", "ДАТА ГОЙДЫ", "ВРЕМЯ ГОЙДЫ"),
            Event("ГОЙДА", "ДАТА ГОЙДЫ", "ВРЕМЯ ГОЙДЫ"),
            Event("ГОЙДА", "ДАТА ГОЙДЫ", "ВРЕМЯ ГОЙДЫ")
        )

        // Настройка RecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.addItemDecoration(SpacingItemDecoration(4))
        recyclerView.adapter = EventAdapter(items)


        return view
    }
}