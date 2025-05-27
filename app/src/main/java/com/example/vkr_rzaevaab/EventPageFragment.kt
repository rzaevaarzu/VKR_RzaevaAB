package com.example.vkr_rzaevaab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.vkr_rzaevaab.api.viewmodels.EventRegistrViewModel
import com.example.vkr_rzaevaab.api.viewmodels.EventViewModel
import com.example.vkr_rzaevaab.databinding.FragmentEventPageBinding
import com.example.vkr_rzaevaab.entities.Event
import com.example.vkr_rzaevaab.entities.EventRegistration
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EventPageFragment : Fragment() {

    val bundle = Bundle()
    private var _binding: FragmentEventPageBinding? = null
    private val binding: FragmentEventPageBinding get() = _binding!!

    private val eventViewModel: EventViewModel by viewModels()
    private val eventRegistrViewModel: EventRegistrViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentEventPageBinding.inflate(inflater, container, false)

        val user_id = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()


        val item = arguments?.getParcelable<Event>("event")
        val event_id = item!!.id
        binding.eventName.text = item!!.name.toString()
        binding.eventInfo.text = item!!.info.toString()
        binding.date.text = item!!.date.toString()
        binding.time.text = item!!.time.toString()

        binding.eventRegistrButton.setOnClickListener {
            eventRegistrViewModel.createEventReg(EventRegistration(null, userId = user_id, eventId = event_id))
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(requireContext(), "Запись успешно добавлена", Toast.LENGTH_SHORT).show()
            }
//            bundle.putString("doc", doctor_id.toString())
//            Navigation.findNavController(it).navigate(R.id.action_doctorPageFragment_to_addMeetingFragment, bundle)
//
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}