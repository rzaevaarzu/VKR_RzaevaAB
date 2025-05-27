package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr_rzaevaab.api.adapters.EventAdapter
import com.example.vkr_rzaevaab.api.viewmodels.EventViewModel
import com.example.vkr_rzaevaab.databinding.FragmentEventListBinding
import com.example.vkr_rzaevaab.entities.Event
import com.example.vkr_rzaevaab.entities.EventItem
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EventListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    private var _binding: FragmentEventListBinding? = null
    private val binding: FragmentEventListBinding get() = _binding!!

    private val eventViewModel: EventViewModel by viewModels()
    private var gList = listOf<Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)

        eventViewModel.getAllEvents()

        binding.eventList.layoutManager = LinearLayoutManager(context)
        val navController = findNavController()
        eventViewModel.eventList.observe(this as LifecycleOwner){ it ->
            adapter = eventViewModel.createAdapter(it)
            gList = it
            binding.eventList.adapter = adapter
            adapter!!.onItemClick = {
                val bundle = Bundle()
                bundle.putParcelable("event", it)
                navController.navigate(
                    R.id.action_eventListFragment_to_eventPageFragment,
                    bundle
                )
            }
        }

        return binding.root
    }
}